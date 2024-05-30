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

//    @Transactional
//    fun signUpUser(request: SignUpUserRequest):Boolean{
//        try {
//            userRepository.save(request.toEntity(
//                encoder.hashPassword(request.password)))
//        } catch (e: DataIntegrityViolationException) {
//            throw ServiceException("Data Duplication")
//        }
//        return true//임의로 넣음. 팀원과 조정할 것

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