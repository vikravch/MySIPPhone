package com.vikravch.mysipphone.sip_telephony.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MySipInput(
    modifier: Modifier,
    messageText: String,
    setMessageText: (String) -> Unit,
    placeholder: String = ""
) {
    TextField(value = messageText, onValueChange = setMessageText, label = {
        Text(text = placeholder)
    }, placeholder = {
        Text(text = placeholder)
    },
        maxLines = 5,
        modifier = Modifier.padding(16.dp))

}