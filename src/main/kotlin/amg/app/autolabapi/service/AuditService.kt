package amg.app.autolabapi.service

import amg.app.autolabapi.model.Audit
import amg.app.autolabapi.model.AuditType
import amg.app.autolabapi.repository.AuditRepository
import amg.app.autolabapi.repository.AuditTypeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuditService(
    private val auditRepository: AuditRepository,
    private val auditTypeRepository: AuditTypeRepository
) {
    fun findAllAudits(): List<Audit> = auditRepository.findAll()

    @Transactional
    fun createAudit(audit: Audit): Audit = auditRepository.save(audit)

    fun findAllAuditTypes(): List<AuditType> = auditTypeRepository.findAll()

    @Transactional
    fun createAuditType(auditType: AuditType): AuditType = auditTypeRepository.save(auditType)
}
