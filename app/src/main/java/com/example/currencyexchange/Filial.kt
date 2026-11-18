package com.example.currencyexchange

import android.R.attr.font
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.setValue
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FilialCard(filial: Filial) {
    val context = LocalContext.current
    var isExpanded by remember { mutableStateOf(false)}

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {
                    Toast.makeText(
                        context,
                        filial.name,
                        Toast.LENGTH_LONG
                    ).show()
                },
                onLongClick = {isExpanded = !isExpanded}
            ),
        elevation = CardDefaults.cardElevation(2.dp)
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = filial.street_type + " " + filial.street,
                fontSize = 14.sp
            )
            if (isExpanded){
                Column(
                    modifier = Modifier.fillMaxWidth(),
                     horizontalAlignment = Alignment.CenterHorizontally,
                 ) {
                    Text(
                        text = "USD: ${filial.USD_in} / ${filial.USD_out}",
                        fontSize = 14.sp
                    )
                    Text(
                        text = "EUR: ${filial.EUR_in} / ${filial.EUR_out}",
                        fontSize = 14.sp
                    )
                    Text(
                        text = "CHY: ${filial.CNY_in} / ${filial.CNY_out}",
                        fontSize = 14.sp
                    )
                    Text(
                        text = "RUB: ${filial.RUB_in} / ${filial.RUB_out}",
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Обновлено: ${filial.kurs_date_time}",
                        fontSize = 12.sp
                    )
                }
            }
        }

    }


}