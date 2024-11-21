package com.vikravch.mysipphone.sip_telephony.presentation.page.profile

sealed class ProfileTabEvent {
    data object LoadUser : ProfileTabEvent()
    data class SaveProfileData(val userName: String, val password: String) : ProfileTabEvent()
    data object CloseProfile : ProfileTabEvent()
}