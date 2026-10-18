package com.example.crm_nexora.domain.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import androidx.exifinterface.media.ExifInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.math.min
import kotlin.math.roundToInt

object ImageCompressor {

    private const val MAX_BYTES: Long = 2048L * 1024L
    private const val MAX_WIDTH = 1920
    private const val MAX_HEIGHT = 1080
    private const val MIN_QUALITY = 10
    private const val QUALITY_STEP = 5

    suspend fun compressToUploadFile(context: Context, uri: Uri): File =
        withContext(Dispatchers.IO) {
            val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            val stream = context.contentResolver.openInputStream(uri)
                ?: throw IOException("Cannot open image")
            stream.use { BitmapFactory.decodeStream(it, null, bounds) }

            if (bounds.outWidth <= 0 || bounds.outHeight <= 0) {
                throw IOException("Cannot decode image dimensions")
            }

            val sampleSize = calcSampleSize(bounds.outWidth, bounds.outHeight)
            val opts = BitmapFactory.Options().apply { inSampleSize = sampleSize }
            val bitmap = context.contentResolver.openInputStream(uri)?.use {
                BitmapFactory.decodeStream(it, null, opts)
            } ?: throw IOException("Cannot decode image")

            val rotated = fixRotation(context, uri, bitmap)
            val resized = resizeBitmap(rotated, MAX_WIDTH, MAX_HEIGHT)
            if (rotated !== bitmap) rotated.recycle()
            bitmap.recycle()

            val outFile = File(context.cacheDir, "upload_${System.currentTimeMillis()}.jpg")
            var quality = 100
            do {
                FileOutputStream(outFile).use {
                    resized.compress(Bitmap.CompressFormat.JPEG, quality, it)
                }
                quality -= QUALITY_STEP
            } while (outFile.length() > MAX_BYTES && quality >= MIN_QUALITY)

            resized.recycle()
            outFile
        }

    private fun calcSampleSize(width: Int, height: Int): Int {
        var sampleSize = 1
        var halfWidth = width / 2
        var halfHeight = height / 2
        while (halfWidth / sampleSize >= MAX_WIDTH || halfHeight / sampleSize >= MAX_HEIGHT) {
            sampleSize *= 2
        }
        return sampleSize
    }

    private fun resizeBitmap(bitmap: Bitmap, maxWidth: Int, maxHeight: Int): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val ratio = min(maxWidth.toFloat() / width, maxHeight.toFloat() / height)
        if (ratio >= 1f) return bitmap

        val newWidth = (ratio * width).roundToInt()
        val newHeight = (ratio * height).roundToInt()
        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
    }

    private fun fixRotation(context: Context, uri: Uri, bitmap: Bitmap): Bitmap {
        val exif = context.contentResolver.openInputStream(uri)?.use { ExifInterface(it) }
            ?: return bitmap
        val deg = when (exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL,
        )) {
            ExifInterface.ORIENTATION_ROTATE_90 -> 90f
            ExifInterface.ORIENTATION_ROTATE_180 -> 180f
            ExifInterface.ORIENTATION_ROTATE_270 -> 270f
            else -> 0f
        }
        if (deg == 0f) return bitmap
        return Bitmap.createBitmap(
            bitmap, 0, 0, bitmap.width, bitmap.height,
            Matrix().apply { postRotate(deg) }, true,
        )
    }
}
