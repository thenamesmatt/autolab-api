package amg.app.autolabapi.dto

data class UserDto (
    val username: String,
    val email: String,
    val avatar: String,
    val bio: String,
    val token: String
)