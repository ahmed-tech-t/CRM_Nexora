package com.example.crm_nexora.domain.utils

import androidx.compose.ui.unit.LayoutDirection
import androidx.core.text.TextDirectionHeuristicsCompat
import java.text.Bidi

fun String.capitalizeFirstLetter(): String {
    return replaceFirstChar { it.uppercase() }
}

fun String.CapitalizeFirstLetterOnEachWord(): String {
    return replace(Regex("\\b[A-Za-zА]")) { it.value.uppercase() }
}

fun isRtlText(text: String): Boolean {
    if (text.isBlank()) return false
    return TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR.isRtl(text, 0, text.length)
}

fun String.normalizeWhitespace(): String =
    replace("\r\n", " ")
        .replace('\n', ' ')
        .replace('\r', ' ')
        .replace(Regex(" +"), " ")
        .trim()

fun Int.toBoolean(): Boolean {
    return this == 1
}

fun getLayoutDirection(value: String): LayoutDirection {
    if (value.isEmpty()) return LayoutDirection.Ltr

    val firstLetter = value.firstOrNull { it.isLetter() } ?: return LayoutDirection.Ltr
    val bidi = Bidi(firstLetter.toString(), Bidi.DIRECTION_DEFAULT_LEFT_TO_RIGHT)
    return if (bidi.isLeftToRight) LayoutDirection.Ltr else LayoutDirection.Rtl
}
