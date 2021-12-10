package com.phucynwa.profanity.filter

interface ProfanityFilter {

    fun censor(text: String): String
}
