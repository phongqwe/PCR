package com.qxdzbc.pcr.action.log_out

import com.google.firebase.auth.FirebaseAuth
import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.di.UserMs
import com.qxdzbc.pcr.state.app.FirebaseUserWrapper
import javax.inject.Inject

class LogoutActionImp @Inject constructor(
    @UserMs
    val uMs:Ms<FirebaseUserWrapper?>,
) : LogoutAction {
    override fun logout() {
        FirebaseAuth.getInstance().signOut()
        uMs.value = null
    }
}
