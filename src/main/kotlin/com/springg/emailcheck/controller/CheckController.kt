package com.springg.emailcheck.controller

import com.springg.emailcheck.dto.CheckPassCodeRequest
import com.springg.emailcheck.dto.SendPassCodeRequest
import com.springg.emailcheck.service.MailService
import com.springg.emailcheck.service.PassCodeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users/signup")
@RestController
class CheckController @Autowired constructor (
    private val mailService: MailService,
    private val passCodeService: PassCodeService
){
    @PostMapping("/send-passcode")
    fun sendPassCode(@RequestBody request: SendPassCodeRequest) {
        mailService.sendPassCode(request.email)
    }

    @PostMapping("/check-passcode")
    fun checkPassCode(@RequestBody request: CheckPassCodeRequest): Boolean {
        val savedCode = passCodeService.getPassCode(request.email)
        return if (savedCode != null && savedCode == request.passcode) {
            passCodeService.removePassCode(request.email)
            true
        } else {
            false
        }
    }
}