package com.qxdzbc.pcr.err

import com.github.michaelbull.result.Err

data class ErrorReport(
    val code:String,
    val detail:String,
){
    override fun toString(): String {
        return "${code}: $detail"
    }
}
