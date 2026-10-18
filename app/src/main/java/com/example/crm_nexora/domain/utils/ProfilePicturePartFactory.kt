package com.example.crm_nexora.domain.utils

import android.net.Uri
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

private const val PROFILE_PICTURE_FIELD = "profile_picture"
private const val JPEG_MEDIA_TYPE = "image/jpeg"

fun Uri.toProfilePicturePart(): MultipartBody.Part? {
    if (this == Uri.EMPTY) return null

    val file = toUploadFile() ?: return null
    if (!file.exists() || !file.canRead() || file.length() <= 0L) return null

    val requestBody = file.asRequestBody(JPEG_MEDIA_TYPE.toMediaTypeOrNull())
    return MultipartBody.Part.createFormData(PROFILE_PICTURE_FIELD, file.name, requestBody)
}

private fun Uri.toUploadFile(): File? {
    return when (scheme?.lowercase()) {
        "file" -> path?.let(::File)?.takeIf { it.exists() }
        else -> null
    }
}
