package amg.app.autolabapi.repository

import amg.app.autolabapi.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Int> {
    fun findByUsername(username: String): UserEntity?
    fun findByEmail(email: String): UserEntity?
}
