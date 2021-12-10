package com.phucynwa.profanity.filter.replacement

internal class DefaultReplacementFactory : ReplacementFactory {

    override fun createReplacement(profanity: String): String =
        buildString {
            repeat(profanity.length) { append('*') }
        }
}
