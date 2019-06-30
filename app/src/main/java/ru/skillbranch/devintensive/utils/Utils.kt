package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?) :Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")

        var firstName = parts?.getOrNull(0)
        if (firstName == "") {
            firstName = null
        }
        var lastName = parts?.getOrNull(1)
        if (lastName == "") {
            lastName = null
        }
        return firstName to lastName
    }

    fun transliteration(s: String, divider:String = " "): String {
        val list = mapOf (
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya",
            " " to divider
        )
        var result = ""
        for (c in s) {
            val newChar = list["${c.toLowerCase()}"]
            if (newChar != null) {
                if (newChar.length > 1 && c.isUpperCase()) {
                    result += newChar.first().toUpperCase()
                    result += newChar.last()
                }
                else {
                    result += if (c.isUpperCase()) newChar.toUpperCase() else newChar
                }
            }
            else {
                result += c
            }
        }
        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var firstInitial = firstName?.trim()?.getOrNull(0)?.toUpperCase()
        var secondInitial = lastName?.trim()?.getOrNull(0)?.toUpperCase()
        if (firstInitial != null && secondInitial != null){
            return "$firstInitial$secondInitial"
        }
        if (firstInitial != null) {
            return "$firstInitial"
        }
        if (secondInitial != null) {
            return "$secondInitial"
        }
        return null
    }
}
