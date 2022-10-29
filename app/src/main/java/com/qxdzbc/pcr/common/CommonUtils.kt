package com.qxdzbc.pcr.common

object CommonUtils {
    fun Boolean.toInt():Int{
        if(this) return 1
        else return 0
    }
}
