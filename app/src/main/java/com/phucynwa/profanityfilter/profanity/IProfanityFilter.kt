package com.phucynwa.profanityfilter.profanity

interface IProfanityFilter {

    fun censor(text: String): String
}
