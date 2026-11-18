package com.example.currencyexchange

import java.util.Currency

data class Filial(
     val filial_id: String,
     val sap_id: String?,
     val info_worktime: String,
     val street_type: String,
     val street: String,
     val filials_text: String,
     val home_number: String,
     val name: String,
     val name_type: String,
     val kurs_date_time: String,
     val USD_in: String,
     val USD_out: String,
     val EUR_in: String,
     val EUR_out: String,
     val RUB_in: String,
     val RUB_out: String,
     val CNY_in: String,
     val CNY_out: String,
     val USD_EUR_in: String,
     val USD_EUR_out: String,
     val USD_RUB_in: String,
     val USD_RUB_out: String,
     val RUB_EUR_in: String,
     val RUB_EUR_out: String,
     val CNY_USD_in: String,
     val CNY_USD_out: String,
)

fun Filial.getBuyRate(type: CurrencyType): Double = when (type) {
     CurrencyType.USD -> USD_in
     CurrencyType.EUR -> EUR_in
     CurrencyType.RUB -> RUB_in
     CurrencyType.CNY -> CNY_in
}.toDoubleOrNull() ?: 0.0

fun Filial.getSellRate(type: CurrencyType): Double = when (type) {
     CurrencyType.USD -> USD_out
     CurrencyType.EUR -> EUR_out
     CurrencyType.RUB -> RUB_out
     CurrencyType.CNY -> CNY_out
}.toDoubleOrNull() ?: 0.0