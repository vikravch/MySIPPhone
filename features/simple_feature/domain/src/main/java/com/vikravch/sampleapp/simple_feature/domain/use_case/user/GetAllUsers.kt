package com.vikravch.sampleapp.simple_feature.domain.use_case.user

import com.vikravch.sampleapp.simple_feature.domain.model.User
import com.vikravch.sampleapp.simple_feature.domain.repository.UserRepository

class GetAllUsers(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<List<User>> {
        return userRepository.getUsers()
    }
}