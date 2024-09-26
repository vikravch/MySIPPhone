package com.vikravch.sampleapp.simple_feature.presentation.page

import com.vikravch.sampleapp.simple_feature.domain.model.User

sealed class DataPageEvent {
    data object GetQuote : DataPageEvent()

    data class GetUser(val userId: Int) : DataPageEvent()
    data object GetAllUsers : DataPageEvent()
    data class AddUser(val user: User) : DataPageEvent()
    data class UpdateUser(val user: User) : DataPageEvent()
    data class DeleteUser(val userId: String) : DataPageEvent()

}