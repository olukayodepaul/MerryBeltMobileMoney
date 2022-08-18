package com.example.merrybeltmobilemoney.ui.home.presenters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.TransferEvent
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.TransferState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TransferViewModel @Inject constructor(private val repo: MerryBeltApiRepository): ViewModel() {

    var uiState = MutableStateFlow(TransferState())

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

    private fun onInputtedAccNo(inputtedAccNo: String) {
        uiState.value = uiState.value.copy(
            inputtedAccNo = inputtedAccNo
        )
    }

    private fun onInputtedAccName(inputtedAccName: String) {
        uiState.value = uiState.value.copy(
            inputtedAccName = inputtedAccName
        )
    }

    private fun onChangeInputtedAmount(inputtedAccName: String) {
        uiState.value = uiState.value.copy(
            inputtedAccName = inputtedAccName
        )
    }

    private fun onChangeInputtedRemark(inputtedRemark: String) {
        uiState.value = uiState.value.copy(
            inputtedRemark = inputtedRemark
        )
    }


    fun transEventHandler(transEvent: TransferEvent) {
        when (transEvent) {

            is TransferEvent.OnChangeInputtedAccNo->{
                onInputtedAccNo(transEvent.accNo)
            }

            is TransferEvent.OnChangeInputtedAccName->{
                onInputtedAccName(transEvent.accName)
            }

            is TransferEvent.OnChangeInputtedAmount->{
                onChangeInputtedAmount(transEvent.amount)
            }

            is TransferEvent.OnChangeInputtedRemark->{
                onChangeInputtedRemark(transEvent.remark)
            }

            else -> {}
        }
    }


    init {
        viewModelScope.launch {
            onBalance(balances = repo.customerProfile().balance)
            onAccountNumber(accountNumber = repo.customerProfile().accountNumber)
        }
    }

}