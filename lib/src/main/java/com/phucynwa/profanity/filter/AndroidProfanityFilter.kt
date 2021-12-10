package com.phucynwa.profanity.filter

import com.phucynwa.profanity.filter.dictionary.Dictionary
import com.phucynwa.profanity.filter.replacement.DefaultReplacementFactory
import com.phucynwa.profanity.filter.replacement.ReplacementFactory

open class AndroidProfanityFilter(
    private val dictionary: Dictionary,
    private val replacementFactory: ReplacementFactory = DefaultReplacementFactory()
) : ProfanityFilter {

    override fun censor(text: String): String {
        val badWords = dictionary.search(text)
        return if (badWords.isEmpty()) text else clearProfanityWords(text, badWords)
    }

    private fun clearProfanityWords(text: String, badWords: Set<String>): String {
        var newText = text
        badWords.forEach { word ->
            val badWord = word.removeSpaces().lowercase()
            val replacement = replacementFactory.createReplacement(badWord)
            newText = newText.replaceCompound(badWord, replacement)
        }
        return newText
    }
}
