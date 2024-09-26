package com.vikravch.sampleapp.simple_feature.domain.use_case.user

import com.vikravch.sampleapp.simple_feature.domain.model.User
import com.vikravch.sampleapp.simple_feature.domain.repository.UserRepository

class AddUser(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User): Result<Boolean> {
        return userRepository.createUser(user)
    }
}