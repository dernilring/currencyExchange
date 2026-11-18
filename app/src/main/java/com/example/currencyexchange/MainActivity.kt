package com.example.currencyexchange

import android.os.Bundle
import android.util.Log.v
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.currencyexchange.ui.theme.CurrencyExchangeTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrencyExchangeTheme {
                CurrencyScreen()
            }
        }
    }
}

@Composable
fun CurrencyScreen(viewModel: CurrencyViewModel = viewModel()) {

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        var query by rememberSaveable { mutableStateOf("") }
        var selectedCurrency by rememberSaveable { mutableStateOf(CurrencyType.USD) }
        var sortAscending by rememberSaveable { mutableStateOf(true) }
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            when {
                viewModel.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                viewModel.errorMessage != null -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = viewModel.errorMessage.orEmpty(),
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(onClick = { viewModel.fetchFilials() }) {
                            Text(text = "retry")
                        }
                    }

                }

                else -> {
                    val allCities = viewModel.filials.map {it.name}.distinct()

                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        CityFilter(allCities, query, { query = it})
                        Sorting(selectedCurrency,
                            {selectedCurrency = it},
                            sortAscending,
                            {sortAscending = !sortAscending})

                        val filteredAndSortedFilials = viewModel.filials
                            .filter { query.isBlank() || it.name.contains(query, ignoreCase = true) }
                            .filter { it.getBuyRate(selectedCurrency) > 0.0 }
                            .sortedBy { it.getBuyRate(selectedCurrency) }
                            .let { if (!sortAscending) it.reversed() else it }

                        if (filteredAndSortedFilials.isEmpty()) {
                            Text(text = "нет совпадений",
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(26.dp))
                        }
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(16.dp) ,
                            contentPadding = PaddingValues(16.dp),
                        ) {
                            items(filteredAndSortedFilials, key = { it.filial_id }) { filial ->
                                FilialCard(filial)
                            }
                        }
                    }

                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun CurrencyPreview() {
    CurrencyExchangeTheme {

    }
}