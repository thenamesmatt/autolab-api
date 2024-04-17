package amg.app.autolabapi.service

import amg.app.autolabapi.model.Email
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service

@Service
class EmailService(private val emailSender: JavaMailSender) {

    /**
     * Opens a requests through Javamail to send an email
     * using the email parameter passed in as the data
     *
     * @param email : Email
     */
    fun sendEmail(email: Email): Boolean {
        return try {
            val message = emailSender.createMimeMessage()
            val helper = MimeMessageHelper(message, true)

            helper.setTo("autolabreviews@gmail.com")
            helper.setSubject(email.subject)
            helper.setText(email.body, true) // true indicates HTML content

            emailSender.send(message)
            true
        } catch (e: Exception) {
            false
        }
    }
}