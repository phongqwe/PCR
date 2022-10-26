package com.qxdzbc.pcr.state.app

import com.google.firebase.auth.FirebaseUser

class FirebaseUserWrapperImp(private val fu:FirebaseUser) : FirebaseUserWrapper {
    override val uid: String
        get() = fu.uid
    override val displayName: String?
        get() = fu.displayName
    override val email: String?
        get() = fu.email
}
