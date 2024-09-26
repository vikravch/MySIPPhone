package com.vikravch.sampleapp.simple_feature.domain.use_case.user

import com.vikravch.sampleapp.simple_feature.domain.use_case.quote.GetQuote

data class UsersUseCases(
    val getAllUsers: GetAllUsers,
    val getUser: GetUser,
    val addUser: AddUser,
    val updateUser: UpdateUser,
    val deleteUser: DeleteUser
)
