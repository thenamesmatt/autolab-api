package amg.app.autolabapi.model

/**
 * @param name The users name
 * @param email The users email
 * @param subject Subject of the email
 * @param body Body text of the email which will be HTML as a string
 */
data class Email (
    val name: String,
    val email: String,
    val subject: String,
    val body: String
)