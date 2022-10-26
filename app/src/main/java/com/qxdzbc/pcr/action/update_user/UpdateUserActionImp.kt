package com.qxdzbc.pcr.action.update_user

import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.di.DefaultUserMs
import com.qxdzbc.pcr.state.app.FirebaseUserWrapper
import javax.inject.Inject

class UpdateUserActionImp @Inject constructor(
    @DefaultUserMs
    val userMs:Ms<FirebaseUserWrapper?>,
) : UpdateUserAction {
    override fun updateUser(i: FirebaseUserWrapper?) {
        userMs.value = i
    }

    override fun removeUser() {
        updateUser(null)
    }
}
