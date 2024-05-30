package com.springg.emailcheck.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SignUpService @Autowired constructor(
    private val mailService: MailService,
    private val passCodeService: PassCodeService
) {
    fun sendPassCode(email: String) {
        mailService.sendPassCode(email)
    }

    fun checkPassCode(email: String, code: Int): Boolean {
        val savedCode = passCodeService.getPassCode(email)
        return if (savedCode != null && savedCode == code) {
            passCodeService.removePassCode(email)
            true
        } else {
            false
        }
    }

    fun signUp(email: String, code: Int): String {
        val savedCode = passCodeService.getPassCode(email)
        return if (savedCode != null && savedCode == code) {
            passCodeService.removePassCode(email)
            "Signup successful"
        } else {
            "Signup failed"
        }
    }
}