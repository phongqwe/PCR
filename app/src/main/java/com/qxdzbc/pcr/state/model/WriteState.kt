package com.qxdzbc.pcr.state.model

enum class WriteState{
    WritePending, DeletePending, OK;

    companion object {
        fun String.toWriteState():WriteState?{
            return when(this){
                WritePending.name -> WritePending
                DeletePending.name->DeletePending
                OK.name->OK
                else -> null
            }
        }
    }
}
