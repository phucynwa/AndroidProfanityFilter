package com.phucynwa.profanityfilter.profanity

interface Dictionary {

    fun search(text: String): Set<String>
    fun isProfane(phrase: String): Boolean
    fun size(): Int
}
