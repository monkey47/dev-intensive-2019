package ru.skillbranch.devintensive.extensions

fun String.truncate(count:Int = 16):String {
    val newString = this.trim()
    if (newString.length > count) {
        return "${newString.substring(0, count).trim()}..."
    }
    return newString
}

fun String.stripHtml():String{
    return this.replace("</?[^>]+>".toRegex(), "")
        .replace("\\s+".toRegex(), " ")
}