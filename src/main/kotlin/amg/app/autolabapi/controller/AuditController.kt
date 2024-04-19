package amg.app.autolabapi.controller

import amg.app.autolabapi.entities.AuditEntity
import amg.app.autolabapi.entities.AuditTypeEntity
import amg.app.autolabapi.service.AuditService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/audits")
class AuditController(private val auditService: AuditService) {

    @GetMapping
    fun listAudits(): List<AuditEntity> = auditService.findAllAudits()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createAudit(@RequestBody auditEntity: AuditEntity): AuditEntity = auditService.createAudit(auditEntity)

    @GetMapping("/types")
    fun listAuditTypes(): List<AuditTypeEntity> = auditService.findAllAuditTypes()

    @PostMapping("/types")
    @ResponseStatus(HttpStatus.CREATED)
    fun createAuditType(@RequestBody auditTypeEntity: AuditTypeEntity): AuditTypeEntity = auditService.createAuditType(auditTypeEntity)
}
