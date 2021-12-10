package com.phucynwa.profanityfilter.profanity

import android.content.Context
import com.hankcs.algorithm.AhoCorasickDoubleArrayTrie
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class PlainDictionary(context: Context, filePath: String) : Dictionary {

    private val dictionary = AhoCorasickDoubleArrayTrie<String>()
    private val badWordsCompounds = mutableMapOf<String, String>()

    init {
        loadDictionary(context, filePath)
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
        bufferedReader.useLines { lines ->
            lines.forEach { line ->
                val key = line.replace(" ", ""
                ).lowercase()
                badWordsCompounds[key] = line
            }
        }
        dictionary.build(badWordsCompounds)
    }

    override fun search(text: String): Set<String> {
        return searchBadWords(text)
    }

    override fun isProfane(phrase: String): Boolean {
        val keyword = phrase.lowercase().replace(NOT_TEXT_REGEX, "")
        val exactMatch = dictionary.exactMatchSearch(keyword)
        return exactMatch > 0
    }

    override fun size(): Int {
        return dictionary.size()
    }

    private fun searchBadWords(input: String): Set<String> {
        val foundBadWords = mutableSetOf<String>()
        var checkText = input.lowercase()
        val badWordList = loadBadWordList(checkText.replace(NOT_TEXT_REGEX, ""))
        for (hit in badWordList) {
            val profanePhrase = hit.value
            if (TextUtils.containsCompound(checkText, profanePhrase)) {
                badWordsCompounds[profanePhrase]?.let { foundBadWords.add(it) }
                checkText = TextUtils.replaceCompound(checkText, profanePhrase, "")
            }
        }
        return foundBadWords
    }

    private fun loadBadWordList(input: String): List<AcdatHit<String>> {
        return dictionary.parseText(input).sortedByDescending { it?.value?.length }
    }

    companion object {
        private val NOT_TEXT_REGEX = "[^a-zA-Z0-9]".toRegex()
    }
}

@JvmInline
value class KHit<T>(val hit: AhoCorasickDoubleArrayTrie.Hit<T>) {

    inline val value: T? get() = hit.value
}

typealias AcdatHit<T> = AhoCorasickDoubleArrayTrie.Hit<T>
