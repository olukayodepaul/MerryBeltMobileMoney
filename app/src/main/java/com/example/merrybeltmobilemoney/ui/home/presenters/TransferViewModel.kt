package com.example.merrybeltmobilemoney.ui.home.presenters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.*
import com.example.merrybeltmobilemoney.util.Constant.gson
import com.example.merrybeltmobilemoney.util.EncryptionUtil
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

    private fun onExpanded(expanded: Boolean) {
        uiState.value = uiState.value.copy(
            expanded = expanded
        )
    }

    private fun onSpecimenText(specimen: String) {

        uiState.value = uiState.value.copy(
            specimen = specimen
        )

        //empty the account number
        uiState.value = uiState.value.copy(
            accNoToTransferTo = ""
        )

    }

    private fun onSetListOfBanks(listOfBanks: List<AllBanks>) {
        uiState.value = uiState.value.copy(
            listOfBanks = listOfBanks,
        )
    }

    private fun onSelectedBankLogo(selectedBankLogo: String) {
        uiState.value = uiState.value.copy(
            selectedBankLogo = selectedBankLogo
        )
    }

    private fun onAccNoToTransferTo(accNoToTransferTo: String) = viewModelScope.launch{

        uiState.value = uiState.value.copy(
            accNoToTransferTo = accNoToTransferTo
        )

        if(accNoToTransferTo.length==10 && uiState.value.specimen.isNotEmpty()) {

            val isSelectedBankCode = uiState.value.setBankCode

            val validateAccNumber = ValidateAccNumber(
                bankCode = isSelectedBankCode,
                accountNumber = accNoToTransferTo
            )

            try {

                val accValidResponse = repo.validateAccNumber(repo.customerProfile().terminalId, repo.customerProfile().sessionId, validateAccNumber)
                val decryptedData = EncryptionUtil().isDecryption(accValidResponse.body()!!.data, repo.customerProfile().sessionId)
                val getValid:ValidationData = gson.fromJson(decryptedData, ValidationData::class.java)
                onAccNameToTransferTo(accNameToTransferTo = getValid.accountName!!)

            }catch (e:Throwable){
                Log.d("CHECKEPO ERROR", "${e.message}")
            }

        }
    }

    private fun onAccNameToTransferTo(accNameToTransferTo: String) {
        uiState.value = uiState.value.copy(
            accNameToTransferTo = accNameToTransferTo
        )
    }

    //set bank code
    private fun onBankCode(setBankCode: String) {
        uiState.value = uiState.value.copy(
            setBankCode = setBankCode
        )
    }

    fun transEventHandler(transEvent: TransferEvent) {
        when (transEvent) {

            is TransferEvent.OnExpanded->{
                onExpanded(transEvent.expanded)
            }

            is TransferEvent.OnSpecimenText->{
                onSpecimenText(transEvent.specimen)
            }

            is TransferEvent.OnSelectedBankLogo->{
                onSelectedBankLogo(transEvent.bankLogo)
            }

            is TransferEvent.OnAccNoToTransferTo->{
                onAccNoToTransferTo(transEvent.accNoToTransferTo)
            }

            is TransferEvent.OnSetBankCode->{
                onBankCode(transEvent.setBankCode)
            }

        }
    }


    init {
        viewModelScope.launch {

            onBalance(balances = repo.customerProfile().balance)
            try{

                val dataFromEncryptedBankList = repo.getEncryptedBankList(repo.customerProfile().terminalId, repo.customerProfile().sessionId)
                val decryptedData = EncryptionUtil().isDecryption(dataFromEncryptedBankList.body()!!.data, repo.customerProfile().sessionId)
                val getListOfBanks: List<AllBanks> = gson.fromJson(decryptedData, Array<AllBanks>::class.java).toList()
                onSetListOfBanks(listOfBanks = getListOfBanks)

            }catch (e:Throwable) {
                Log.d("ISEPOKHAI 2", "${e.message}")
            }

        }
    }

}