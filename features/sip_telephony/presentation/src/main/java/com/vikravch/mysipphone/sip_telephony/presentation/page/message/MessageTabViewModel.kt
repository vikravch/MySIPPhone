package com.vikravch.mysipphone.sip_telephony.presentation.page.message

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.vikravch.mysipphone.sip_telephony.domain.use_cases.telephony_use_case.TelephonyUseCase
import kotlinx.coroutines.Dispatchers

@HiltViewModel
class MessageTabViewModel @Inject constructor(
    private val telephonyUseCase: TelephonyUseCase
): ViewModel() {


    fun onEvent(event: MessageTabEvent) {
        when (event) {
            is MessageTabEvent.SendMessage -> {
                viewModelScope.launch(Dispatchers.IO) {
                    telephonyUseCase.sendMessage(
                        "vitkravch",
                        "${event.recipient}@sip2sip.info",
                        "sip2sip.info",
                        event.message
                    )
                }
            }
        }
    }
}