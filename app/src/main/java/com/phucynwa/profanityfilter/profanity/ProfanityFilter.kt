package com.phucynwa.profanityfilter.profanity

import android.content.Context

class ProfanityFilter(context: Context, filePath: String = "bad_words_en_us.txt") : IProfanityFilter {

    private val dictionary = PlainDictionary(context, filePath)

    override fun censor(text: String): String {
        val badWords = dictionary.search(text)
        if (badWords.isEmpty()) {
            return text
        }
        return clearProfanityWords(text, badWords)
    }

    private fun clearProfanityWords(text: String, badWords: Set<String>): String {
        var newText = text
        badWords.forEach { word ->
            val badWord = word.replace(" ", "").lowercase()
            val replacement = createWordReplacement(badWord.length)
            newText = TextUtils.replaceCompound(newText, badWord, replacement)
        }
        return newText
    }

    private fun createWordReplacement(length: Int) = buildString {
        repeat(length) { append('*') }
    }

    companion object {

        private var INSTANCE: ProfanityFilter? = null

        fun getInstance(
            context: Context, filePath: String = "badwords_en_us.txt"
        ): ProfanityFilter {
            return INSTANCE ?: synchronized(this) {
                ProfanityFilter(context, filePath).also { INSTANCE = it }
            }
        }
    }
}
