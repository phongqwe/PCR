package com.qxdzbc.pcr.common

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import com.github.michaelbull.result.Result
import com.qxdzbc.pcr.err.ErrorReport

typealias Rs<T,E> = Result<T, E>
typealias Rse<T> = Result<T, ErrorReport>
typealias Ms<T> = MutableState<T>
typealias St<T> = State<T>
