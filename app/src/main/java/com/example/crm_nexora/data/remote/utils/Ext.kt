package com.example.crm_nexora.data.remote.utils

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.core.text.HtmlCompat
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

fun String.toTextRequestBody(): RequestBody =
    this.toRequestBody("text/plain".toMediaTypeOrNull())

fun String?.toPlainTextFromHtml(): String =
    HtmlCompat.fromHtml(
        this.orEmpty(),
        HtmlCompat.FROM_HTML_MODE_LEGACY,
    ).toString()
        .replace(Regex("[\\t\\n\\r]+"), " ")
        .replace(Regex(" {2,}"), " ")
        .trim()

fun File.toMultipart(paramName: String): MultipartBody.Part {
    val mediaType = when (extension.lowercase()) {
        "jpg", "jpeg" -> "image/jpeg"
        "png" -> "image/png"
        "pdf" -> "application/pdf"
        else -> "application/octet-stream"
    }.toMediaTypeOrNull()

    val requestFile = asRequestBody(mediaType)
    return MultipartBody.Part.createFormData(paramName, name, requestFile)
}

fun Uri.toMultipartBodyPart(
    paramName: String,
    context: Context,
    fileName: String? = null,
): MultipartBody.Part {
    val resolver = context.contentResolver
    val mimeType = resolver.getType(this)
        ?: when (fileName?.substringAfterLast('.', "")?.lowercase()) {
            "jpg", "jpeg" -> "image/jpeg"
            "png" -> "image/png"
            "pdf" -> "application/pdf"
            else -> "application/octet-stream"
        }
    val fileName = fileName
        ?: resolver.query(this, arrayOf(OpenableColumns.DISPLAY_NAME), null, null, null)
            ?.use { cursor ->
                val i = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (cursor.moveToFirst() && i >= 0) cursor.getString(i) else null
            }
        ?: "document"
    val bytes = resolver.openInputStream(this)?.use { it.readBytes() }
        ?: error("Unable to open uri: $this")

    val requestBody = bytes.toRequestBody(mimeType.toMediaTypeOrNull())
    return MultipartBody.Part.createFormData(paramName, fileName, requestBody)
}
