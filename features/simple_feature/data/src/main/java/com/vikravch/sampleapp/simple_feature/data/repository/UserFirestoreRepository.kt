package com.vikravch.sampleapp.simple_feature.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.vikravch.sampleapp.simple_feature.data.remote.firestore.UserFirestoreDTO
import com.vikravch.sampleapp.simple_feature.data.toUser
import com.vikravch.sampleapp.simple_feature.domain.model.User
import com.vikravch.sampleapp.simple_feature.domain.repository.UserRepository
import kotlinx.coroutines.tasks.await

class UserFirestoreRepository(val firestore: FirebaseFirestore): UserRepository {
    override suspend fun getUser(id: String): Result<User> {
        val user = firestore.collection("users").document(id).get().await().toObject(
            UserFirestoreDTO::class.java)
            ?: return Result.failure(Exception("User not found"))
        return Result.success(user.toUser())
    }
    override suspend fun getUsers(): Result<List<User>> {
        val users = firestore.collection("users").get().await().toObjects(UserFirestoreDTO::class.java)
        return Result.success(users.map { user-> user.toUser() })
    }
    override suspend fun updateUser(user: User): Result<Boolean> {
        firestore.collection("users").document(user.id).set(user).await()
        return Result.success(true)
    }
    override suspend fun deleteUser(id: String): Result<Unit> {
        firestore.collection("users").document(id).delete().await()
        return Result.success(Unit)
    }
    override suspend fun createUser(user: User): Result<Boolean> {
        val document = firestore.collection("users").get().await()
        val newUser = user.copy(id = (document.documents.size+1).toString())
        firestore.collection("users").document((document.documents.size+1).toString()).set(newUser).await()

        return Result.success(true)
    }
}