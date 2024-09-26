package com.vikravch.sampleapp.simple_feature.presentation.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.vikravch.sampleapp.simple_feature.domain.model.User
import com.vikravch.sampleapp.ui.theme.SampleAppTheme

@Composable
fun DataPage(viewModel: DataPageViewModel = hiltViewModel()) {
    LaunchedEffect(key1 = true) {
        viewModel.onEvent(DataPageEvent.GetQuote)
    }

    SampleAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Text(text = "Data Page")

                Button(onClick = { viewModel.onEvent(DataPageEvent.GetQuote) }) {
                    Text(text = "Get Quote")
                }
                Button(onClick = { viewModel.onEvent(DataPageEvent.GetUser(1)) }) {
                    Text(text = "Get Users")
                }
                Button(onClick = {
                    viewModel.onEvent(
                        DataPageEvent.AddUser(User( name = "John Doe", email = "test@gmail.com") )
                    ) }) {
                    Text(text = "Add User")
                }
            }
        }
    }
}