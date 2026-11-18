package com.example.currencyexchange

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Sorting(
    selectedCurrency: CurrencyType,
    onCurrencySelected: (CurrencyType) -> Unit,
    sortAscending: Boolean,
    onSortToggle: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Выбор валюты
        CurrencyType.values().forEach { type ->
            FilterChip(
                selected = selectedCurrency == type,
                onClick = { onCurrencySelected(type) },
                label = { Text(type.label) }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = onSortToggle) {
            Text(if (sortAscending) "Min" else "Max")
        }
    }
}