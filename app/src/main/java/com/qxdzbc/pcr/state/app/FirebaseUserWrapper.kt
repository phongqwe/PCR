package com.qxdzbc.pcr.state.app

import com.google.firebase.auth.FirebaseUser
import com.qxdzbc.pcr.firestore.UserDoc

interface FirebaseUserWrapper {
    val uid:String
    val displayName: String?
    val email: String?

    fun toUserDoc():UserDoc{
        return UserDoc(id = uid)
    }

    companion object{
        fun FirebaseUser.toWrapper():FirebaseUserWrapper{
            return FirebaseUserWrapperImp(this)
        }
        val forPreview:FirebaseUserWrapper = MockFirebaseUserWrapper()
    }
}
