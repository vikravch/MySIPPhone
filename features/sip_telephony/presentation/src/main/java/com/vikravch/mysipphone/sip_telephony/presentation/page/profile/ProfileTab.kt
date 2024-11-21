package com.vikravch.mysipphone.sip_telephony.presentation.page.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vikravch.mysipphone.sip_telephony.presentation.component.MySipInput
import com.vikravch.sampleapp.ui.theme.SampleAppTheme

@Composable
fun ProfileTab(modifier: Modifier, viewModel: ProfileTabViewModel = hiltViewModel()) {
    
    val (username, setUsername) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(
            ProfileTabEvent.LoadUser
        )
    }
    
    SampleAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if(viewModel.state.isRegistered) {
                    Box(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Button(onClick = {
                            viewModel.onEvent(
                                ProfileTabEvent.CloseProfile
                            )
                        }) {
                            Text(text = "Unregister")
                        }
                    }
                } else {
                    MySipInput(
                        modifier = Modifier,
                        messageText = username,
                        setMessageText = setUsername,
                        placeholder = "Username"
                    )

                    MySipInput(
                        modifier = Modifier,
                        messageText = password,
                        setMessageText = setPassword,
                        placeholder = "Password"
                    )
                    Box(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Button(onClick = {
                            if(username.isNotEmpty() && password.isNotEmpty()) {
                                viewModel.onEvent(
                                    ProfileTabEvent.SaveProfileData(
                                        userName = username,
                                        password = password
                                    )
                                )
                            }
                        }) {
                            Text(text = "Register")
                        }
                    }
                }
            }
        }
    }
}