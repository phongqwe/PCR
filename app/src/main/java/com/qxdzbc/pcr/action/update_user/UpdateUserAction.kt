package com.qxdzbc.pcr.action.update_user

import com.qxdzbc.pcr.state.app.FirebaseUserWrapper

interface UpdateUserAction {
    fun updateUser(i:FirebaseUserWrapper?)
    fun removeUser()
}
