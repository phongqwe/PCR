package com.qxdzbc.pcr.state.app

class MockFirebaseUserWrapper(
    override val uid: String = "mock_user_id",
    override val displayName: String = "mock user name",
    override val email: String = "mockUser@email.com",
    ) : FirebaseUserWrapper {

}
