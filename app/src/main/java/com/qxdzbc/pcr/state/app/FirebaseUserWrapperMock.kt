package com.qxdzbc.pcr.state.app

class FirebaseUserWrapperMock(
    override val uid: String = "uid",
    override val displayName: String = "user's display name",
    override val email: String = "user@email.com",
    ) : FirebaseUserWrapper {

}
