package amg.app.autolabapi.controller

import amg.app.autolabapi.model.Email
import amg.app.autolabapi.service.EmailService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class EmailController(
    private val emailService: EmailService,
) {

    /**
     * This endpoint takes in the email body from the front end AutoLab Webapp
     * and sends an email to the autolabreviews@gmail.com from the support email
     *
     * @param email the incoming email body that holds all email data
     */
    @PostMapping("/email")
    fun sendEmail(@RequestBody email: Email): ResponseEntity<Any> {
        return if (emailService.sendEmail(email)) {
            ResponseEntity.ok().body("Email sent successfully.")
        } else {
            ResponseEntity.status(500).body("Failed to send email.")
        }
    }
}