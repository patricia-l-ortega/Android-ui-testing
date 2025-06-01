package com.cognizant.ortegapatricia.brownbagsession_uitesting.data

import android.util.Log
import com.cognizant.ortegapatricia.brownbagsession_uitesting.data.model.LoggedInUser
import java.io.IOException
import java.util.UUID

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    private val staticUsername = "voojwan"
    private val staticPassword = "Test12345"

    fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            // TODO: handle loggedInUser authentication
            Log.d(">>credentials", "username: $username, password: $password")
            if (username == staticUsername && password == staticPassword) {
                val fakeUser = LoggedInUser(UUID.randomUUID().toString(), "Jane Doe")
                return Result.Success(fakeUser)
            } else {
                return Result.Error(IOException("Username or password does not match"))
            }
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}