package amg.app.autolabapi.model

import jakarta.persistence.*


@Entity
@Table(name = "audit_type")
class AuditType(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var auditTypeId: Int? = null,

    @Column(nullable = false)
    var typeName: String = "",  // Default value set

    var description: String? = null  // Nullable field with default null
)

