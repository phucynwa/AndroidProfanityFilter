package com.phucynwa.profanity.filter

fun String.containsCompound(compound: String): Boolean {
    val compoundRegex = compound.createCompoundRegex()
    return compoundRegex.containsMatchIn(this)
}

fun String.createCompoundRegex(): Regex {
    return map { char -> "(" + char.lowercaseChar() + "|" + char.uppercaseChar() + ')' }
        .joinToString("([^a-zA-Z0-9]|\\s*)")
        .toRegex()
}

fun String.replaceCompound(compound: String, replacement: String): String {
    val compoundRegex = compound.createCompoundRegex()
    return replace(compoundRegex, replacement)
}

fun String.removeSpaces() = replace(" ", "")

fun String.removeAll(regex: Regex) = replace(regex, "")
