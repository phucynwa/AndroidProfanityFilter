package com.phucynwa.profanityfilter.profanity

object TextUtils {
    fun containsCompound(source: String, compound: String): Boolean {
        val compoundRegex = createCompoundRegex(compound)
        return compoundRegex.containsMatchIn(source)
    }

    private fun createCompoundRegex(compound: String): Regex {
        return compound
            .map { char -> "(${char.lowercaseChar()}|${char.uppercaseChar()})" }
            .joinToString("\\s*")
            .toRegex()
    }

    fun replaceCompound(text: String, compound: String, wordReplacement: String?): String {
        val compoundRegex = createCompoundRegex(compound)
        println(compoundRegex)
        return text.replace(compoundRegex, wordReplacement!!)
    }
}
