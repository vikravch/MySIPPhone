package com.vikravch.sampleapp

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.vikravch.sampleapp.simple_feature.presentation.page.data_page.DataPage
import com.vikravch.sampleapp.simple_feature.presentation.page.data_page.DataPageViewModel
import com.vikravch.sampleapp.simple_feature.presentation.page.quote_page.QuotePage
import com.vikravch.sampleapp.simple_feature.presentation.page.quote_page.QuotePageViewModel
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
fun MainActivityUI(
    quotePageViewModel: QuotePageViewModel = hiltViewModel(),
    dataPageViewModel: DataPageViewModel = hiltViewModel()
) {
    SampleAppTheme {
        val items = listOf("Data", "Quote")
        var selectedItem by remember { mutableIntStateOf(0) } // To track the selected item index

        Scaffold(
            bottomBar = { BottomBar(items, selectedItem) { selectedItem = it } },
            content = { paddingValues ->
                // Change content based on the selected item
                when (selectedItem) {
                    0 -> DataPage(modifier = Modifier.padding(paddingValues), viewModel = dataPageViewModel)
                    1 -> QuotePage(modifier = Modifier.padding(paddingValues), viewModel = quotePageViewModel)
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
                icon = { Icon(Icons.Default.Home, contentDescription = null) }, // Customize icons as needed
                selected = selectedItem == index, // Check if the item is selected
                onClick = {
                    onItemSelected(index) // Update selected item on click
                }
            )
        }
    }
}

