package com.vikravch.mysipphone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.vikravch.mysipphone.sip_telephony.presentation.page.message.MessageTab
import com.vikravch.mysipphone.sip_telephony.presentation.page.profile.ProfileTab
import com.vikravch.sampleapp.ui.theme.SampleAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainActivityUI()
        }
    }
}

@Composable
fun MainActivityUI() {
    SampleAppTheme {
        val items = listOf("Profile", "Message")
        var selectedItem by remember { mutableIntStateOf(0) } // To track the selected item index

        Scaffold(
            bottomBar = { BottomBar(items, selectedItem) { selectedItem = it } },
            content = { paddingValues ->
                when (selectedItem) {
                    0 -> ProfileTab(modifier = Modifier.padding(paddingValues))
                    1 -> MessageTab(modifier = Modifier.padding(paddingValues))
                }
            }
        )
    }
}

@Composable
fun BottomBar(items: List<String>, selectedItem: Int, onItemSelected: (Int) -> Unit) {
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                label = { Text(item) },
                icon = {
                    Icon(
                        Icons.Default.Home,
                        contentDescription = null
                    )
                },
                selected = selectedItem == index,
                onClick = {
                    onItemSelected(index)
                }
            )
        }
    }
}

