package com.example.currencyexchange

import android.util.Log
import android.util.Log.e
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.internal.dns.DnsMessage.Companion.response

class CurrencyViewModel : ViewModel(){
    var filials by mutableStateOf<List<Filial>>(emptyList())
    var errorMessage by mutableStateOf<String?>(null)
    var isLoading by mutableStateOf(false)

    init {
        fetchFilials()
    }

    fun fetchFilials(){
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
        try{
             filials = RetrofitInstance.apiService.getCurrency()

        } catch (e: Exception){
            errorMessage = e.localizedMessage ?: "loading data error"
            Log.e("CurrencyViewModel", "loading data error", e)
        } finally {
             isLoading = false
        }
        }
    }
}