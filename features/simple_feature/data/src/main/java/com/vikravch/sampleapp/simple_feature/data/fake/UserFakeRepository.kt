package com.vikravch.sampleapp.simple_feature.data.fake

import com.vikravch.sampleapp.simple_feature.domain.model.User
import com.vikravch.sampleapp.simple_feature.domain.repository.UserRepository

class UserFakeRepository: UserRepository {

    override suspend fun getUser(id: String): Result<User> {
        return Result.success(User(id = "1", name = "John Doe", email = "test@user"))
    }

    override suspend fun getUsers(): Result<List<User>> {
        return Result.success(
            listOf(
                User(id = "1", name = "John Doe", email = "test@user"),
                User(id = "2", name = "Jane Doe", email = "test@user")
            )
        )
    }

    override suspend fun updateUser(user: User): Result<Boolean> {
        return Result.success(true)
    }

    override suspend fun deleteUser(id: String): Result<Unit> {
        return Result.success(Unit)
    }

    override suspend fun createUser(user: User): Result<Boolean> {
        return Result.success(true)
    }
}