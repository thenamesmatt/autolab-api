package amg.app.autolabapi.repository

import amg.app.autolabapi.model.Audit
import org.springframework.data.jpa.repository.JpaRepository

interface AuditRepository : JpaRepository<Audit, Int>
