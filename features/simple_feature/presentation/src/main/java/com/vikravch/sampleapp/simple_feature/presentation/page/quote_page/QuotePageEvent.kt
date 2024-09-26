package com.vikravch.sampleapp.simple_feature.presentation.page.quote_page

sealed class QuotePageEvent {
    data object GetQuote : QuotePageEvent()
}