package com.springg.emailcheck

import com.springg.emailcheck.service.MailService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext

@SpringBootApplication
class EmailCheckApplication

fun main(args: Array<String>) {
    val context: ApplicationContext = runApplication<EmailCheckApplication>(*args)
    val mailService = context.getBean(MailService::class.java)
    mailService.sendPassCode("test@mailyouwant.com")
}