package amg.app.autolabapi.controller

import amg.app.autolabapi.model.Audit
import amg.app.autolabapi.model.AuditType
import amg.app.autolabapi.service.AuditService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/audits")
class AuditController(private val auditService: AuditService) {

    @GetMapping
    fun listAudits(): List<Audit> = auditService.findAllAudits()

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createAudit(@RequestBody audit: Audit): Audit = auditService.createAudit(audit)

    @GetMapping("/types")
    fun listAuditTypes(): List<AuditType> = auditService.findAllAuditTypes()

    @PostMapping("/types")
    @ResponseStatus(HttpStatus.CREATED)
    fun createAuditType(@RequestBody auditType: AuditType): AuditType = auditService.createAuditType(auditType)
}
