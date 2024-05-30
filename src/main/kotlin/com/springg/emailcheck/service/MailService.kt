package com.springg.emailcheck.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class MailService @Autowired constructor
    (
    private val mailSender: JavaMailSender,
    private val passCodeService: PassCodeService,
) {

    fun sendPassCode(to: String) {
        val passCode = generatePassCode()
        passCodeService.savePassCode(to, passCode)
        val message = createMessage(to, passCode)
        try {
            mailSender.send(message)
            println("Verification code sent to $to: $passCode")
        } catch (e: Exception) {
            println("Failed to send email: ${e.message}")
            e.printStackTrace()
        }

    }

    private fun generatePassCode(length: Int = 5): Int {
        return Random.nextInt(10000, 100000)
    }

    private fun createMessage(to: String, passCode: Int): SimpleMailMessage {
        return SimpleMailMessage().apply {
            setFrom("sender@naver.com")
            setTo(to)
            setSubject("Your Verification Code")
            setText("Your verification code is: $passCode")
        }
    }
}