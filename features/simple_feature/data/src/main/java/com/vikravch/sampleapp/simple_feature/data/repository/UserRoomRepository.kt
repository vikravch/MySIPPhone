package com.vikravch.sampleapp.simple_feature.data.repository

import com.vikravch.sampleapp.simple_feature.data.database.dao.UserDao
import com.vikravch.sampleapp.simple_feature.data.toUser
import com.vikravch.sampleapp.simple_feature.data.toUserEntity
import com.vikravch.sampleapp.simple_feature.domain.model.User
import com.vikravch.sampleapp.simple_feature.domain.repository.UserRepository

class UserRoomRepository(
    private val userDao: UserDao
): UserRepository {

    override suspend fun getUser(id: String): Result<User> {
        val userEntity = userDao.loadAllByIds(intArrayOf(id.toInt())).first()
        return Result.success(userEntity.toUser())
    }
    override suspend fun getUsers(): Result<List<User>> {
        val userEntities = userDao.getAll()
        return Result.success(userEntities.map { it.toUser() })
    }
    override suspend fun updateUser(user: User): Result<Boolean> {
        Result.success(userDao.updateUser(user.toUserEntity()))
        return Result.success(true)
    }
    override suspend fun deleteUser(id: String): Result<Unit> {
        return Result.success(userDao.delete(userDao.loadAllByIds(intArrayOf(id.toInt())).first()))
    }
    override suspend fun createUser(user: User): Result<Boolean> {
        userDao.insertAll(user.toUserEntity())
        return Result.success(true)
    }
}