package com.example.crm_nexora.domain.utils

import com.example.crm_nexora.BuildConfig

fun resolveProfileImageUrl(url: String?): String? {
    if (url.isNullOrBlank()) return null

    val sanitized = url.trim()
        .replace(" ", "%20")
        .replace("(", "%28")
        .replace(")", "%29")

    if (sanitized.startsWith("http://", ignoreCase = true) ||
        sanitized.startsWith("https://", ignoreCase = true)
    ) {
        return sanitized
    }

    val base = BuildConfig.BASE_URL.removeSuffix("/")
    val path = if (sanitized.startsWith("/")) sanitized else "/$sanitized"
    return base + path
}
