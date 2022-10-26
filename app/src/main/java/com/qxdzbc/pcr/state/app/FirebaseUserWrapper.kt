package com.qxdzbc.pcr.state.app

import com.google.firebase.auth.FirebaseUser

interface FirebaseUserWrapper {
    val uid:String

    companion object{
        fun FirebaseUser.toWrapper():FirebaseUserWrapper{
            return FirebaseUserWrapperImp(this)
        }
    }
}
