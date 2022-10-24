package com.qxdzbc.pcr.common

import androidx.compose.runtime.mutableStateOf

object StateUtils {
    /**
     * a convenient function for mutableStateOf
     */
    fun <T> ms(o: T): Ms<T> {
        return mutableStateOf(o)
    }
}
