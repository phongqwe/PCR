package com.qxdzbc.pcr.state.app

import com.google.firebase.auth.FirebaseUser

class FirebaseUserWrapperImp(private val fu:FirebaseUser) : FirebaseUserWrapper {
    override val uid: String
        get() = fu.uid
}
