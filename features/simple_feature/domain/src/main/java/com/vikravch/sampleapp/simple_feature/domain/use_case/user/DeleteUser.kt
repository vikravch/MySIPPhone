package com.vikravch.sampleapp.simple_feature.domain.use_case.user

import com.vikravch.sampleapp.simple_feature.domain.repository.UserRepository

class DeleteUser(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(id: String) {
        userRepository.deleteUser(id)
    }
}