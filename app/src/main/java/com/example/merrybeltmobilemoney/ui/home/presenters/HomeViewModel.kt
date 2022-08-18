package com.example.merrybeltmobilemoney.ui.home.presenters


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.ui.home.dashboard.home_data.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: MerryBeltApiRepository):ViewModel() {

    var uiState = MutableStateFlow(HomeState())

    private fun onBalance(balances: String) {
        uiState.value = uiState.value.copy(
            balances = balances
        )
    }

    private fun onAccountNumber(accountNumber: String) {
        uiState.value = uiState.value.copy(
            accountNumber = accountNumber
        )
    }

    init {
        viewModelScope.launch {
            onBalance(balances = repo.customerProfile().balance)
            onAccountNumber(accountNumber = repo.customerProfile().accountNumber)
        }
    }

}