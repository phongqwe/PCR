package com.qxdzbc.pcr.state.model

enum class EntryState{
    WritePending, DeletePending, OK;

    companion object {
        fun String.toEntryState():EntryState?{
            return when(this){
                WritePending.name -> WritePending
                DeletePending.name->DeletePending
                OK.name->OK
                else -> null
            }
        }
    }
}
