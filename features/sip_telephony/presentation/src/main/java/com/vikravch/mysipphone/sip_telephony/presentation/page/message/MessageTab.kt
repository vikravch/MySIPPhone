package com.vikravch.mysipphone.sip_telephony.presentation.page.message

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
fun MessageTab(modifier: Modifier, viewModel: MessageTabViewModel = hiltViewModel()) {

    // message text state
    val (recipient, setRecipient) = remember { mutableStateOf("") }
    val (messageText, setMessageText) = remember { mutableStateOf("") }

    SampleAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

                MySipInput(
                    modifier = Modifier,
                    messageText = recipient,
                    setMessageText = setRecipient,
                    placeholder = "Enter recipient",
                )

                TextField(value = messageText, onValueChange = setMessageText, label = {
                    Text(text = "Message Text")
                }, placeholder = {
                    Text(text = "Enter message text")
                },
                    maxLines = 5,
                    modifier = Modifier.padding(16.dp))
                Box(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Button(
                        onClick = {
                            if (recipient.isNotEmpty() && messageText.isNotEmpty()){
                                viewModel.onEvent(
                                    MessageTabEvent.SendMessage(recipient, messageText)
                                )
                                setMessageText("")
                            }
                        }) {
                        Text(text = "Send Message")
                    }
                }

            }
        }
    }
}