package com.example.merrybeltmobilemoney.ui.home.transfer.transfer_presenter

import android.util.Log
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
                bankList = bankList,
                bankLoader = false
            )
        }
    }

    private fun onBankExpanded(bankExpanded: Boolean) {
        uiState.update {
            it.copy(
                bankExpanded = bankExpanded
            )
        }
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
    }


    private fun onAccountNumber(accountNumber: String) = viewModelScope.launch {
        uiState.update {
            it.copy(
                accountNumber = accountNumber
            )
        }

        if (accountNumber.length == 10 && uiState.value.bankSelected.isNotEmpty()) {

            onBankLoader(bankLoader = true)

            val getBankCode = uiState.value.backCode

            val validateAccNumber =
                ValidateAccNumber(bankCode = getBankCode, accountNumber = accountNumber)

            try {

                val accValidResponse = repo.validateAccNumber(
                    repo.customerProfile().terminalId,
                    repo.customerProfile().sessionId,
                    validateAccNumber
                )

                if (accValidResponse.body()!!.status!!) {

                    val decryptedData = EncryptionUtil().isDecryption(accValidResponse.body()!!.data, repo.customerProfile().sessionId)
                    val getValid: ValidationData = gson.fromJson(decryptedData, ValidationData::class.java)
                    onAccountName(accountName = getValid.accountName!!)

                } else {
                    uiState.update { it.copy(accountName = "") }
                    Toast.makeText(
                        appContext,
                        accValidResponse.body()!!.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Throwable) {
                uiState.update {

                    it.copy(
                        accountName = "",
                        msgTitle = "Error",
                        msgVisibility = true,
                        msg = "Invalid Account Details"
                    )
                }
            }
            onBankLoader(bankLoader = false)
        }
    }

    private fun onAccountName(accountName: String) {
        uiState.update { it.copy(accountName = accountName) }
    }

    private fun onAmount(amount: String) {
        uiState.update { it.copy(amount = amount) }
    }

    private fun onBankLoader(bankLoader: Boolean) {
        uiState.update { it.copy(bankLoader = bankLoader) }
    }

    private fun onMessageDialog(msgTitle: String, msgVisibility: Boolean, msg: String) {
        uiState.update {
            it.copy(
                msgTitle = msgTitle,
                msgVisibility = msgVisibility,
                msg = msg
            )
        }
    }

    private fun onContinue() {
        if (uiState.value.bankSelected.isEmpty() || uiState.value.bankSelected.isEmpty() || uiState.value.backCode.isEmpty() || uiState.value.amount.isEmpty()) {
            onMessageDialog(
                msgTitle = "Enter Field Error",
                msgVisibility = true,
                msg = "Please all the field"
            )

        } else {

            if (uiState.value.accountNumber.length == 10) {
                onHideAnsShowPinDialog(hideAnsShowPinDialog = true)
            } else {
                onMessageDialog(
                    msgTitle = "NUBAN NUMBER ERROR",
                    msgVisibility = true,
                    msg = "Please check the NUBAN NUMBER"
                )
            }
        }
    }


    private fun onHideAnsShowPinDialog(hideAnsShowPinDialog: Boolean) {
        uiState.update {
            it.copy(
                hideAnsShowPinDialog = hideAnsShowPinDialog,
                bankPin = ""
            )
        }
    }

    private fun onBankPin(bankPin: String) {
        uiState.update { it.copy(bankPin = bankPin) }
    }

    private fun onTransfer() = viewModelScope.launch {
        if (uiState.value.bankPin.isEmpty()) {
            onMessageDialog(
                msgTitle = "PIN ERROR",
                msgVisibility = true,
                msg = "please enter 4-digit Pin"
            )
        } else {

            onBankLoader(bankLoader = true)

            try {

                val requestBodyParam = FundTrans(
                    uiState.value.amount,
                    repo.customerProfile().stan,
                    uiState.value.bankPin,
                    uiState.value.accountNumber,
                    uiState.value.backCode,
                    type = "TRANSFER"
                )
                    val isFundTransfer = repo.fundTransfer(repo.customerProfile().terminalId, repo.customerProfile().sessionId, requestBodyParam)
                    val decryptedData = EncryptionUtil().isDecryption(isFundTransfer.body()!!.data, repo.customerProfile().sessionId)
                    val getValid: DecryptedTransData = gson.fromJson(decryptedData, DecryptedTransData::class.java)

                    if (getValid.responseCode!! == "00") {
                        onMessageDialog(
                            msgTitle = "TRANSFER ERROR",
                            msgVisibility = true,
                            msg = getValid.description!!
                        )
                    } else {
                        uiState.update {
                            it.copy(successHideAndShowDialog = true)
                        }
                    }

            } catch (e: Throwable) {
                onMessageDialog(
                    msgTitle = "Throwable",
                    msgVisibility = true,
                    msg = e.message.toString()
                )
            }

            onBankLoader(bankLoader = false)
        }
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
            is TransferEvent.OnBankLoader -> {
                onBankLoader(transEvent.bankLoader)
            }
            is TransferEvent.OnMessageDialog -> {
                onMessageDialog(transEvent.msgTitle, transEvent.msgVisibility, transEvent.msg)
            }
            is TransferEvent.OnContinue -> {
                onContinue()
            }
            is TransferEvent.OnHideAnsShowPinDialog -> {
                onHideAnsShowPinDialog(transEvent.hideAnsShowPinDialog)
            }
            is TransferEvent.OnBankPin -> {
                onBankPin(transEvent.bankPin)
            }
            is TransferEvent.OnTransfer -> {
                onTransfer()
            }
        }
    }

    init {
        viewModelScope.launch {
            try {
                onBankLoader(bankLoader = true)
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
                onBankLoader(bankLoader = false)
            }
        }
    }

}