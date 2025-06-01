package com.cognizant.ortegapatricia.brownbagsession_uitesting.data.model

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)