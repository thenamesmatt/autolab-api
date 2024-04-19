package amg.app.autolabapi.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "audits")
class AuditEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var auditId: Int? = null,

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "audit_type_id", nullable = false)
    var auditTypeEntity: AuditTypeEntity = AuditTypeEntity(),  // Ensure default instance is provided

    @Column(nullable = false)
    var performedBy: Int = 0,  // Default value for primitives

    @Column(nullable = false)
    var performedOn: LocalDateTime = LocalDateTime.now(),

    var details: String? = null
)
