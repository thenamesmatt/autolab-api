package amg.app.autolabapi.repository

import amg.app.autolabapi.entities.AuditTypeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AuditTypeRepository : JpaRepository<AuditTypeEntity, Int>
