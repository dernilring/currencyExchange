package com.example.currencyexchange

import android.util.Log.e
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

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
            val response = RetrofitInstance.apiService.getCurrency()
            filials =response.filials

        } catch (e: Exception){

        } finally {
             isLoading = false
        }
}
    }
}