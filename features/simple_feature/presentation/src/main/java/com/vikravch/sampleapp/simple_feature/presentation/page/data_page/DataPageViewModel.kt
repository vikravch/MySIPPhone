package com.vikravch.sampleapp.simple_feature.presentation.page.data_page

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikravch.sampleapp.simple_feature.domain.use_case.quote.QuoteUseCases
import com.vikravch.sampleapp.simple_feature.domain.use_case.user.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataPageViewModel @Inject constructor(
    private val quoteUseCases: QuoteUseCases,
    private val usersUseCases: UsersUseCases
): ViewModel() {

    fun onEvent(event: DataPageEvent) {
        when (event) {
            is DataPageEvent.AddUser -> {
                viewModelScope.launch(Dispatchers.IO){
                    val resultData = usersUseCases.addUser(event.user).getOrNull()
                    Log.d("ViewModel", "Add User: $resultData")
                }
            }
            is DataPageEvent.DeleteUser -> {
                viewModelScope.launch(Dispatchers.IO){
                    val resultData = usersUseCases.deleteUser(event.userId)
                    Log.d("ViewModel", "Delete User: $resultData")
                }
            }

            is DataPageEvent.GetUser -> {
                viewModelScope.launch(Dispatchers.IO){
                    val resultData = usersUseCases.getAllUsers().getOrNull()
                    Log.d("ViewModel", "Users: $resultData")
                }
            }
            is DataPageEvent.UpdateUser -> {
                viewModelScope.launch(Dispatchers.IO){
                    val resultData = usersUseCases.updateUser(event.user).getOrNull()
                    Log.d("ViewModel", "Update User: $resultData")
                }
            }

            DataPageEvent.GetAllUsers -> {
                viewModelScope.launch(Dispatchers.IO){
                    val resultData = usersUseCases.getAllUsers().getOrNull()
                    Log.d("ViewModel", "Users: $resultData")
                }
            }
        }
    }
}