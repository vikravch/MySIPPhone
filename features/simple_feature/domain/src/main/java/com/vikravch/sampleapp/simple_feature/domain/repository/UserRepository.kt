package com.vikravch.sampleapp.simple_feature.domain.repository

import com.vikravch.sampleapp.simple_feature.domain.model.User

interface UserRepository {
    suspend fun getUser(id: String): Result<User>
    suspend fun getUsers(): Result<List<User>>
    suspend fun updateUser(user: User): Result<Boolean>
    suspend fun deleteUser(id: String): Result<Unit>
    suspend fun createUser(user: User): Result<Boolean>
}