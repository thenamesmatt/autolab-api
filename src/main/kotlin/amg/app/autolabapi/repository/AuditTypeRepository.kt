package amg.app.autolabapi.repository

import amg.app.autolabapi.model.AuditType
import org.springframework.data.jpa.repository.JpaRepository

interface AuditTypeRepository : JpaRepository<AuditType, Int>
