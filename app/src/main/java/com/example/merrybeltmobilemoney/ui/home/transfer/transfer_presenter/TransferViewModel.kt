package com.example.merrybeltmobilemoney.ui.home.transfer.transfer_presenter

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merrybeltmobilemoney.Application
import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.*
import com.example.merrybeltmobilemoney.util.Constant.gson
import com.example.merrybeltmobilemoney.util.EncryptionUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TransferViewModel @Inject constructor(
    private val repo: MerryBeltApiRepository,
    private val appContext: Application
) : ViewModel() {

    var uiState = MutableStateFlow(TransferState())

    private fun bankTranList(bankList: List<AllBanks>) {
        uiState.update {
            it.copy(
                bankList = bankList
            )
        }
//        uiState.value = uiState.value.copy(
//            bankList = bankList
//        )
    }

    private fun onBankExpanded(bankExpanded: Boolean) {
        uiState.update {
            it.copy(
                bankExpanded = bankExpanded
            )
        }
//        uiState.value = uiState.value.copy(
//            bankExpanded = bankExpanded
//        )
    }

    private fun onBankSelected(bankSelected: String, bankImage: String, backCode: String) {
        uiState.update {
            it.copy(
                bankSelected = bankSelected,
                bankImage = bankImage,
                backCode = backCode,
                accountNumber = "",
                accountName = ""
            )
        }
//        uiState.value = uiState.value.copy(
//            bankSelected = bankSelected,
//            bankImage = bankImage,
//            backCode = backCode,
//            accountNumber = "",
//            accountName = ""
//        )
    }


    private fun onAccountNumber(accountNumber: String) = viewModelScope.launch {
        uiState.update {
            it.copy(
                accountNumber = accountNumber
            )
        }
//        uiState.value = uiState.value.copy(
//            accountNumber = accountNumber
//        )

        if (accountNumber.length == 10 && uiState.value.bankSelected.isNotEmpty()) {

            uiState.update {
                it.copy(
                    bankLoader = true
                )
            }
//            uiState.value = uiState.value.copy(
//                bankLoader = true
//            )

            val getBankCode = uiState.value.backCode

            val validateAccNumber =
                ValidateAccNumber(bankCode = getBankCode, accountNumber = accountNumber)

            try {

                val accValidResponse = repo.validateAccNumber(repo.customerProfile().terminalId, repo.customerProfile().sessionId, validateAccNumber)

                if (accValidResponse.body()!!.status!!) {

                    val decryptedData = EncryptionUtil().isDecryption(accValidResponse.body()!!.data, repo.customerProfile().sessionId)
                    val getValid:ValidationData = gson.fromJson(decryptedData, ValidationData::class.java)
                    onAccountName(accountName = getValid.accountName!!)

                } else {
                    uiState.update { it.copy(accountName = "") }
                    //uiState.value = uiState.value.copy(accountName = "")
                    Toast.makeText(appContext, accValidResponse.body()!!.message, Toast.LENGTH_SHORT).show()
                }

            } catch (e: Throwable) {
               // uiState.value = uiState.value.copy(accountName = "")
                uiState.update { it.copy(accountName = "") }
                Toast.makeText(appContext, "Invalid Account Number", Toast.LENGTH_SHORT).show()
            }
            uiState.update { it.copy(bankLoader = false) }
            //uiState.value = uiState.value.copy(bankLoader = false)
        }
    }

    private fun onAccountName(accountName: String) {
        uiState.update { it.copy(accountName = accountName) }
//        uiState.value = uiState.value.copy(
//            accountName = accountName
//        )
    }

    private fun onAmount(amount: String) {
        uiState.update { it.copy(amount = amount) }
//        uiState.value = uiState.value.copy(
//            amount = amount
//        )
    }

    private fun onBankLoader(bankLoader: Boolean) {
        uiState.update { it.copy(bankLoader = bankLoader) }
//        uiState.value = uiState.value.copy(
//            bankLoader = bankLoader
//        )
    }

    fun transEventHandler(transEvent: TransferEvent) {
        when (transEvent) {
            is TransferEvent.OnBankExpanded -> {
                onBankExpanded(transEvent.bankExpanded)
            }
            is TransferEvent.OnBankSelected -> {
                onBankSelected(transEvent.bankSelected, transEvent.bankImage, transEvent.backCode)
            }
            is TransferEvent.OnAccountNumber -> {
                onAccountNumber(transEvent.accountNumber)
            }
            is TransferEvent.OnAccountName -> {
                onAccountName(transEvent.accountName)
            }
            is TransferEvent.OnAmount -> {
                onAmount(transEvent.amount)
            }
            is TransferEvent.OnBankLoader->{
                onBankLoader(transEvent.bankLoader)
            }
        }
    }

    init {
        viewModelScope.launch {
            try {
                val dataFromEncryptedBankList = repo.getEncryptedBankList(
                    repo.customerProfile().terminalId,
                    repo.customerProfile().sessionId
                )
                val decryptedData = EncryptionUtil().isDecryption(
                    dataFromEncryptedBankList.body()!!.data,
                    repo.customerProfile().sessionId
                )
                val getListOfBanks: List<AllBanks> =
                    gson.fromJson(decryptedData, Array<AllBanks>::class.java).toList()
                bankTranList(getListOfBanks)
            } catch (e: Throwable) {
            }
        }
    }

}