package com.vikravch.mysipphone.sip_telephony.presentation.page.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.vikravch.mysipphone.sip_telephony.domain.use_cases.telephony_use_case.TelephonyUseCase
import com.vikravch.mysipphone.sip_telephony.presentation.page.message.MessageTabState
import kotlinx.coroutines.Dispatchers

@HiltViewModel
class ProfileTabViewModel @Inject constructor(
    private val telephonyUseCase: TelephonyUseCase
): ViewModel() {


    var state by mutableStateOf(ProfileTabState())
        private set

    fun onEvent(event: ProfileTabEvent) {
        when (event) {
            is ProfileTabEvent.SaveProfileData -> {
                viewModelScope.launch(Dispatchers.IO) {
                    telephonyUseCase.saveProfileData(
                        event.userName,
                        event.password,
                        "sip2sip.info"
                    )
                    state = state.copy(isRegistered = true)
                }
            }
            ProfileTabEvent.CloseProfile -> {
                viewModelScope.launch(Dispatchers.IO) {
                    telephonyUseCase.closeProfile(
                        "",
                        "",
                        "sip2sip.info"
                    )
                    state = state.copy(isRegistered = false)
                }
            }

            ProfileTabEvent.LoadUser -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val profileData = telephonyUseCase.getProfileData()
                    Log.d("ProfileTabViewModel", "Profile data: $profileData")
                    state = state.copy(isRegistered = profileData.isNotEmpty())
                }
            }
        }
    }

    override fun onCleared() {
        viewModelScope.launch(Dispatchers.IO) {
            telephonyUseCase.closeProfile(
                "",
                "",
                "sip2sip.info"
            )
            super.onCleared()
        }
    }
}