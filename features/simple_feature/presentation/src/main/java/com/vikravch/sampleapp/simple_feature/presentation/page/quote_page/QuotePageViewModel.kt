package com.vikravch.sampleapp.simple_feature.presentation.page.quote_page

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikravch.sampleapp.simple_feature.domain.use_case.quote.QuoteUseCases
import com.vikravch.sampleapp.simple_feature.domain.use_case.user.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotePageViewModel @Inject constructor(
    private val useCases: QuoteUseCases,
): ViewModel() {

    var state by mutableStateOf(QuotePageState())
        private set

    fun onEvent(event: QuotePageEvent) {
        when (event) {
            is QuotePageEvent.GetQuote -> {
                viewModelScope.launch{
                    val resultData = useCases.getQuote().getOrNull()
                    Log.d("ViewModel", "Quote: $resultData")
                    state = state.copy(quote = resultData)
                }
            }
        }
    }
}