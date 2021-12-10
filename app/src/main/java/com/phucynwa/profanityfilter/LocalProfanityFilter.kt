package com.phucynwa.profanityfilter

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import com.phucynwa.profanityfilter.profanity.IProfanityFilter
import com.phucynwa.profanityfilter.profanity.ProfanityFilter

val LocalProfanityFilter = ProfanityFilterProvidableCompositionLocal()

@JvmInline
value class ProfanityFilterProvidableCompositionLocal internal constructor(
    private val delegate: ProvidableCompositionLocal<IProfanityFilter?> =
        staticCompositionLocalOf { null }
) {

    val current: IProfanityFilter
        @Composable get() = delegate.current ?: LocalContext.current.profanityFilter

    infix fun provides(value: IProfanityFilter) = delegate provides value

    infix fun providesDefault(value: IProfanityFilter) = delegate providesDefault value
}

inline val Context.profanityFilter: IProfanityFilter
    @JvmName("profanityFilter") get() = ProfanityFilter(this)
