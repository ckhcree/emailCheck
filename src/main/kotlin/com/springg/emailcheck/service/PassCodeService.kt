package com.springg.emailcheck.service

import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class PassCodeService {
    private val passCodes = ConcurrentHashMap<String, Int>()

    fun savePassCode(email: String, passCode: Int) {
        passCodes[email] = passCode
    }

    fun getPassCode(email: String): Int? {
        return passCodes[email]
    }

    fun removePassCode(email: String) {
        passCodes.remove(email)
    }
}