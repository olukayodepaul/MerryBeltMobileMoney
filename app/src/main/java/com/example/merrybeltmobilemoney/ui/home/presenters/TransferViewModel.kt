package com.example.merrybeltmobilemoney.ui.home.presenters

import android.util.Log
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


//
//    private fun onInputtedAccNo(inputtedAccNo: String) {
//
//        uiState.value = uiState.value.copy(
//            inputtedAccNo = inputtedAccNo
//        )
//
//        getBankList(inputtedAccNo)
//    }
//

//
//    private fun getBankList(inputtedAccNo:String) = viewModelScope.launch{
//        if(inputtedAccNo.length==10) {
//
//        }
//    }
//
//
//    private fun onInputtedAccName(inputtedAccName: String) {
//        uiState.value = uiState.value.copy(
//            inputtedAccName = inputtedAccName
//        )
//    }
//
//    private fun onChangeInputtedAmount(inputtedAccName: String) {
//        uiState.value = uiState.value.copy(
//            inputtedAccName = inputtedAccName
//        )
//    }
//
//    private fun onChangeInputtedRemark(inputtedRemark: String) {
//        uiState.value = uiState.value.copy(
//            inputtedRemark = inputtedRemark
//        )
//    }
//
//    private fun onSelectedItemIndex(selectedItemIndex: Int) {
//        uiState.value = uiState.value.copy(selectedItemIndex = selectedItemIndex)
//    }


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


//
//            is TransferEvent.OnChangeInputtedAmount->{
//                onChangeInputtedAmount(transEvent.amount)
//            }
//
//            is TransferEvent.OnChangeInputtedRemark->{
//                onChangeInputtedRemark(transEvent.remark)
//            }
//
//            is TransferEvent.OnSelectedItemIndex -> {
//                onSelectedItemIndex(transEvent.selectedItemIndex)
//            }

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