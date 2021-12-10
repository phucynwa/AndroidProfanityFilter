package com.phucynwa.profanity.filter.dictionary

interface Dictionary {
    val size: Int
    fun search(text: String): Set<String>
}
