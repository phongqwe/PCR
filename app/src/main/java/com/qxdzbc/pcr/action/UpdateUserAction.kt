package com.qxdzbc.pcr.action

import com.qxdzbc.pcr.state.app.FirebaseUserWrapper

interface UpdateUserAction {
    fun updateUser(i:FirebaseUserWrapper?)
    fun removeUser()
}
