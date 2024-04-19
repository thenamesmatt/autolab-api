package amg.app.autolabapi.service

import amg.app.autolabapi.entities.AuditEntity
import amg.app.autolabapi.entities.AuditTypeEntity
import amg.app.autolabapi.repository.AuditRepository
import amg.app.autolabapi.repository.AuditTypeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuditService(
    private val auditRepository: AuditRepository,
    private val auditTypeRepository: AuditTypeRepository
) {
    fun findAllAudits(): List<AuditEntity> = auditRepository.findAll()

    @Transactional
    fun createAudit(auditEntity: AuditEntity): AuditEntity = auditRepository.save(auditEntity)

    fun findAllAuditTypes(): List<AuditTypeEntity> = auditTypeRepository.findAll()

    @Transactional
    fun createAuditType(auditTypeEntity: AuditTypeEntity): AuditTypeEntity = auditTypeRepository.save(auditTypeEntity)
}