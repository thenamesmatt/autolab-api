package amg.app.autolabapi.entities

import jakarta.persistence.*


@Entity
@Table(name = "user")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(nullable = false, unique = true)
    val username: String = "",

    @Column(nullable = false)
    val password: String = "",

    @Column(nullable = false, unique = true)
    val email: String = "",

    val avatar: String? = null,

    val bio: String? = null
)