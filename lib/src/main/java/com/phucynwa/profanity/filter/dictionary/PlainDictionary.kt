package com.phucynwa.profanity.filter.dictionary

import android.content.Context
import com.hankcs.algorithm.AhoCorasickDoubleArrayTrie
import com.phucynwa.profanity.filter.containsCompound
import com.phucynwa.profanity.filter.removeAll
import com.phucynwa.profanity.filter.removeSpaces
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

open class PlainDictionary(context: Context, filePath: String) : Dictionary {

    private val dictionary = AhoCorasickDoubleArrayTrie<String>()
    private val badWordsCompounds = mutableMapOf<String, String>()

    init {
        loadDictionary(context, filePath)
    }

    override val size: Int
        get() = dictionary.size()

    final override fun search(text: String): Set<String> {
        val foundBadWords = mutableSetOf<String>()
        val checkText = text.lowercase().removeAll(NOT_TEXT_REGEX)
        val badWordList = findAllBadWords(checkText)
        for (hit in badWordList) {
            val key = hit.value
            if (checkText.containsCompound(key)) {
                val badWord = badWordsCompounds[key] ?: continue
                foundBadWords.add(badWord)
            }
        }
        return foundBadWords
    }

    private fun loadDictionary(context: Context, filePath: String) {
        val inputStream = try {
            context.assets.open(filePath)
        } catch (e: IOException) {
            e.printStackTrace()
            return
        }

        val inputStreamReader = InputStreamReader(inputStream)
        val bufferedReader = BufferedReader(inputStreamReader)
        bufferedReader.forEachLine { line ->
            val key = line.removeSpaces().lowercase()
            badWordsCompounds[key] = line
        }
        dictionary.build(badWordsCompounds)
    }

    private fun findAllBadWords(input: String): List<AcdatHit> {
        return dictionary.parseText(input)
            .filterNotNull()
    }

    companion object {
        private val NOT_TEXT_REGEX = "[^a-zA-Z0-9]".toRegex()
    }
}

typealias AcdatHit = AhoCorasickDoubleArrayTrie.Hit<String>
