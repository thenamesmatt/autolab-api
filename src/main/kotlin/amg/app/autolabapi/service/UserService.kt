package amg.app.autolabapi.service

import amg.app.autolabapi.entities.UserEntity
import amg.app.autolabapi.repository.UserRepository
import amg.app.autolabapi.util.JwtUtil
import amg.app.autolabapi.util.LoginErrorMessage
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    private val passwordEncoder = BCryptPasswordEncoder()

    fun addUser(username: String, password: String, email: String, avatar: String?, firstName: String, lastName: String, bio: String?): Pair<UserEntity?, String> {

        // check to see if the user already exists
        if (userRepository.findByUsername(username) != null || userRepository.findByEmail(email) != null) {
            return Pair(null, LoginErrorMessage.USER_EXISTS)
        }

        // validate users passed in login data
        if(username == "" ) {
            return Pair(null, LoginErrorMessage.USERNAME_BLANK)
        }

        if(password == "") {
            return Pair(null, LoginErrorMessage.PASSWORD_BLANK)
        }

        if(email == "") {
            return Pair(null, LoginErrorMessage.EMAIL_BLANK)
        }

        // at this point data is good
        val encryptedPassword = passwordEncoder.encode(password)

        val newUser = UserEntity(
            username = username,
            password = encryptedPassword,
            email = email,
            avatar = avatar,
            first_name = firstName,
            last_name = lastName,
            bio = bio,
        )

        userRepository.save(newUser)
        return Pair(newUser, "User created successfully")
    }

    fun validateUser(email: String, password: String): Pair<UserEntity?, String?> {
        val user = userRepository.findByEmail(email)
        return if (user != null) {
            if(passwordEncoder.matches(password, user.password)){
                Pair(user, user.email)
            } else {
                Pair(null, LoginErrorMessage.INVALID_PASSWORD)
            }
        } else {
            Pair(null, LoginErrorMessage.INVALID_EMAIL)
        }
    }

    fun validateUserWithToken(token: String): Pair<UserEntity?, String?> {
        if(JwtUtil.validateToken(token)) {
            val username = JwtUtil.getUsernameFromToken(token)
            val user = userRepository.findByUsername(username ?: "")

            if (user != null) {
                return Pair(user, null)
            }
        }
        return Pair(null, LoginErrorMessage.INVALID_TOKEN)
    }
}
