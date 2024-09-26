package com.vikravch.sampleapp.simple_feature.domain.use_case.user

import com.vikravch.sampleapp.simple_feature.domain.model.User
import com.vikravch.sampleapp.simple_feature.domain.repository.UserRepository

class GetUser(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(id: String): Result<User> {
        return userRepository.getUser(id)
    }
}