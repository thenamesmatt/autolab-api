package amg.app.autolabapi.controller

import amg.app.autolabapi.dto.UserDto
import amg.app.autolabapi.entities.UserEntity
import amg.app.autolabapi.service.UserService
import amg.app.autolabapi.util.JwtUtil
import amg.app.autolabapi.util.TokenModel
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun addUser(@RequestBody user: UserEntity): Triple<UserDto?, HttpStatus, String?> {
        val newUser = userService.addUser(user.username, user.password, user.email, user.avatar, user.bio)
        return newUser.first?.let { user ->
            val token = JwtUtil.generateToken(user.username)
            val userDto = UserDto(
                username = user.username,
                email = user.email,
                avatar = user.avatar ?: "",
                bio = user.bio ?: "",
                token = token
            )
            Triple(userDto, HttpStatus.OK, newUser.second)
        } ?: Triple(null, HttpStatus.UNAUTHORIZED, newUser.second)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginInfo: UserEntity): Triple<UserDto?, HttpStatus, String?> {
        val verifiedUser = userService.validateUser(loginInfo.email, loginInfo.password)

        return verifiedUser.first?.let { user ->
            val token = JwtUtil.generateToken(user.username)
            val userDto = UserDto(
                username = user.username,
                email = user.email,
                avatar = user.avatar ?: "",
                bio = user.bio ?: "",
                token = token
            )
            Triple(userDto, HttpStatus.OK, verifiedUser.second)
        } ?: Triple(null, HttpStatus.UNAUTHORIZED, verifiedUser.second)
    }

    @PostMapping("/validateWithToken")
    fun validateWithToken(@RequestBody tokenModel: TokenModel): Triple<UserDto?, HttpStatus, String?> {
        val verifiedUser = userService.validateUserWithToken(tokenModel.token)

        return verifiedUser.first?.let { user ->

            val userDto = UserDto(
                username = user.username,
                email = user.email,
                avatar = user.avatar ?: "",
                bio = user.bio ?: "",
                token = tokenModel.token
            )
            Triple(userDto, HttpStatus.OK, verifiedUser.second)
        } ?: Triple(null, HttpStatus.UNAUTHORIZED, verifiedUser.second)
    }
}
