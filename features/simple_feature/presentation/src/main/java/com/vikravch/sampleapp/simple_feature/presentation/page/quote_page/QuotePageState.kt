package com.vikravch.sampleapp.simple_feature.presentation.page.quote_page

data class QuotePageState(
    val isLoading: Boolean = false,
    val quote: String? = null,
    val error: String? = null
)