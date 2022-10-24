package com.qxdzbc.pcr.util

import com.google.firebase.auth.FirebaseAuth

object FireAuthUtils {
    fun hasUser():Boolean = FirebaseAuth.getInstance().currentUser!=null
}
