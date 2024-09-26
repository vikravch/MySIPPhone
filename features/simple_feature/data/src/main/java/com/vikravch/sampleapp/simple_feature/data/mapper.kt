package com.vikravch.sampleapp.simple_feature.data

import com.vikravch.sampleapp.simple_feature.data.database.entity.UserEntity
import com.vikravch.sampleapp.simple_feature.data.remote.firestore.UserFirestoreDTO
import com.vikravch.sampleapp.simple_feature.domain.model.User

fun UserEntity.toUser() = User(
    id = uid.toString(),
    name = "$firstName $lastName",
    email = email?:""
)

fun User.toUserEntity() = UserEntity(
    uid = id.toInt(),
    firstName = name.split(" ")[0],
    lastName = name.split(" ")[1],
    email = email
)

fun UserFirestoreDTO.toUser() = User(
    id = id,
    name = name,
    email = email?:""
)