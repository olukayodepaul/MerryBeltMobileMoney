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
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TransferViewModel @Inject constructor(private val repo: MerryBeltApiRepository, private val appContext: Application): ViewModel() {

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

            uiState.value = uiState.value.copy(
                loader = true
            )

            val isSelectedBankCode = uiState.value.setBankCode

            val validateAccNumber = ValidateAccNumber(
                bankCode = isSelectedBankCode,
                accountNumber = accNoToTransferTo
            )

            val accValidResponse = repo.validateAccNumber(repo.customerProfile().terminalId, repo.customerProfile().sessionId, validateAccNumber)
            try {

                if(accValidResponse.body()!!.status!!) {
                    val decryptedData = EncryptionUtil().isDecryption(accValidResponse.body()!!.data, repo.customerProfile().sessionId)
                    val getValid:ValidationData = gson.fromJson(decryptedData, ValidationData::class.java)
                    onAccNameToTransferTo(accNameToTransferTo = getValid.accountName!!)

                    //click here to enable the Continue Button
                    uiState.value = uiState.value.copy(
                        continueButtonEnable = true
                    )

                }else{
                    Toast.makeText(appContext, accValidResponse.body()!!.message, Toast.LENGTH_SHORT).show()
                }

            }catch (e:Throwable) {
                Toast.makeText(appContext, "Please Check Account Number", Toast.LENGTH_SHORT).show()
            }

            uiState.value = uiState.value.copy(
                loader = false
            )

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

    private fun onAmountToTransfer(amountToTransfer: String) {
        uiState.value = uiState.value.copy(
            amountToTransfer = amountToTransfer
        )
    }


    private fun onSetBankName(setBankName: String) {
        uiState.value = uiState.value.copy(
            setBankName = setBankName
        )
    }

    private fun onClickContButton() {
        uiState.value = uiState.value.copy(
            showAndHidePinDialog = true
        )
    }

    private fun onShowAndHidePinDialog(showAndHidePinDialog: Boolean) {
        uiState.value = uiState.value.copy(
            enteringPin = "",
            showAndHidePinDialog = showAndHidePinDialog
        )
    }

    private fun onEnteringPin(enteringPin: String) {
        uiState.value = uiState.value.copy(
            enteringPin = enteringPin
        )
    }

    private fun onClickOnDoneButton() = viewModelScope.launch{
        if(uiState.value.enteringPin.isEmpty()) {
            Toast.makeText(appContext, "Enter your 4-digit Pin", Toast.LENGTH_SHORT).show()
        }else{

           try{
               val isFundTransfer = FundTrans(
                   uiState.value.amountToTransfer,
                   repo.customerProfile().stan,
                   uiState.value.enteringPin,
                   uiState.value.accNoToTransferTo,
                   uiState.value.setBankCode,
                   type = "TRANSFER"
               )

               Log.d("EPOTEXT 1", "${isFundTransfer}")

               val makeTransfer = repo.fundTransfer(
                   repo.customerProfile().terminalId,
                   repo.customerProfile().sessionId,
                   isFundTransfer
               )

               val trans = EncryptionUtil().isDecryption(makeTransfer.body()!!.data, repo.customerProfile().sessionId)
               val getValid: DecryptedTransData = gson.fromJson(trans, DecryptedTransData::class.java)

               if(getValid.description=="00"){
                   Log.d("EPOTEXT 2", "$trans $getValid")
               }else{
                   //close the pin and open a notification dialog for notification messages
                   Log.d("EPOTEXT 2", "$trans $getValid")
               }

           }catch (e:Throwable) {
               Log.d("EPOTEXT 3", "${e.message}")
           }

        }
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

            is TransferEvent.OnAmountToTransfer->{
                onAmountToTransfer(transEvent.amountToTransfer)
            }

            is TransferEvent.OnClickContButton->{
                onClickContButton()
            }

            is TransferEvent.OnSetBankName->{
                onSetBankName(transEvent.setBankName)
            }

            is TransferEvent.OnShowAndHidePinDialog->{
                onShowAndHidePinDialog(transEvent.showAndHidePinDialog)
            }

            is TransferEvent.OnEnteringPin->{
                onEnteringPin(transEvent.enteringPin)
            }

            is TransferEvent.OnClickOnDoneButton->{
                onClickOnDoneButton()
            }
        }
    }

    init {
        viewModelScope.launch {
            try{
                onBalance(balances = repo.customerProfile().balance)
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