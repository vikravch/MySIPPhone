package com.vikravch.sampleapp.simple_feature.presentation.page.quote_page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vikravch.sampleapp.ui.theme.SampleAppTheme

@Composable
fun QuotePage(modifier: Modifier, viewModel: QuotePageViewModel) {
    SampleAppTheme {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            Button(onClick = { viewModel.onEvent(QuotePageEvent.GetQuote) }) {
                Text(text = "Get Quote")
            }
            Text(text = viewModel.state.quote ?: "No Quote")
        }

    }
}