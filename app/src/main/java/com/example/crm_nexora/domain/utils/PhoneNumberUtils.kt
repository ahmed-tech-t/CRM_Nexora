package com.example.crm_nexora.domain.utils

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import java.util.Locale

/**
 * Dial-country used by phone helpers. Kept in utils until domain.models.Country exists.
 */
data class Country(
    val flag: String,
    val nameEn: String,
    val nameAr: String,
    val code: String,
    val phoneLength: Int,
    val regionIso: String = "",
) {
    companion object {
        val Egypt = Country("🇪🇬", "Egypt", "مصر", "+20", 10, "EG")
    }
}

private val phoneUtil: PhoneNumberUtil = PhoneNumberUtil.getInstance()

fun extractLocalPhoneNumber(fullPhone: String): String {
    if (fullPhone.isBlank()) return ""

    val normalized = fullPhone.trim().replace("\\s+".toRegex(), "")
    return try {
        val parsed = phoneUtil.parse(normalized, null)
        phoneUtil.getNationalSignificantNumber(parsed)
    } catch (_: NumberParseException) {
        ""
    }
}

fun resolveCountryFromStoredPhone(fullPhone: String): Country {
    if (fullPhone.isBlank()) return Country.Egypt

    return try {
        val parsed = phoneUtil.parse(fullPhone.trim(), null)
        val regionIso = phoneUtil.getRegionCodeForNumber(parsed) ?: return Country.Egypt
        resolveCountryFromRegionIso(regionIso)
    } catch (_: NumberParseException) {
        Country.Egypt
    }
}

fun buildFullPhoneNumber(localPhone: String, country: Country): String {
    val digits = localPhone.filter { it.isDigit() }
    if (digits.isBlank()) return ""
    return "${country.code}$digits"
}

fun isValidE164Phone(fullPhone: String): Boolean {
    if (fullPhone.isBlank()) return false

    return try {
        val parsed = phoneUtil.parse(fullPhone.trim(), null)
        phoneUtil.isValidNumber(parsed)
    } catch (_: NumberParseException) {
        false
    }
}

private fun resolveCountryFromRegionIso(regionIso: String): Country {
    if (regionIso.equals("EG", ignoreCase = true)) return Country.Egypt

    val dialCode = "+${phoneUtil.getCountryCodeForRegion(regionIso)}"
    if (dialCode == "+0") return Country.Egypt

    val regionName = Locale("", regionIso).displayCountry.ifBlank { regionIso }
    val example = phoneUtil.getExampleNumber(regionIso)
    val phoneLength = example?.let { phoneUtil.getNationalSignificantNumber(it).length } ?: 0

    return Country(
        flag = "",
        nameEn = regionName,
        nameAr = regionName,
        code = dialCode,
        phoneLength = phoneLength,
        regionIso = regionIso.uppercase(Locale.ROOT),
    )
}
