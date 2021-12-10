package com.phucynwa.profanity.filter.replacement

interface ReplacementFactory {
    fun createReplacement(profanity: String): String
}
