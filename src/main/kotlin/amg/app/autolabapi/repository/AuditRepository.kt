package amg.app.autolabapi.repository

import amg.app.autolabapi.entities.AuditEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AuditRepository : JpaRepository<AuditEntity, Int>
