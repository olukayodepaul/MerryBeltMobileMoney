package com.example.merrybeltmobilemoney.ui.home.payorbuy.airtime.airtime_domain


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merrybeltmobilemoney.Application
import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.ui.home.payorbuy.airtime.airtime_data.AirtimeEvent
import com.example.merrybeltmobilemoney.ui.home.payorbuy.airtime.airtime_data.AirtimeState
import com.example.merrybeltmobilemoney.ui.home.payorbuy.airtime.airtime_data.VariousAirtime
import com.example.merrybeltmobilemoney.util.Constant
import com.example.merrybeltmobilemoney.util.EncryptionUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AirtimeViewModel @Inject constructor(
    private val repo: MerryBeltApiRepository,
    private val appContext: Application
) : ViewModel() {

    var uiState = MutableStateFlow(AirtimeState())

    private fun onVariousNetworkAirtimeList(difAirTimeNetwork: List<VariousAirtime>) {
        uiState.value = uiState.value.copy(
            //showAndHideLoader = true,
            difAirTimeNetwork = difAirTimeNetwork
        )
    }

    private fun onExpandNetworkWidget(expandNetworkWidget: Boolean){
        uiState.value = uiState.value.copy(
            expandNetworkWidget = expandNetworkWidget
        )
    }

    private fun onselectedNetwork(selectedNetwork: String){
        uiState.value = uiState.value.copy(
            selectedNetwork = selectedNetwork
        )
    }

    private fun onselectNetworkImage(selectNetworkImage: String){
        uiState.value = uiState.value.copy(
            selectNetworkImage = selectNetworkImage
        )
    }

    private fun onPhoneNumber(phoneNumber: String){
        uiState.value = uiState.value.copy(
            phoneNumber = phoneNumber
        )
    }

    private fun onAmount(amount: String){
        uiState.value = uiState.value.copy(
            amount = amount
        )
    }

    private fun onContinue(){

        if(uiState.value.amount=="" && uiState.value.phoneNumber=="" && uiState.value.selectedNetwork == ""){
            uiState.value = uiState.value.copy(
                messageDialogContent = "Please enter all the field",
                messageDialogShowAndHide = true
            )
        }else{
            uiState.value = uiState.value.copy(
                showAndHidePinDialog = true
            )
        }

    }

    private fun onshowAndHidePinDialog(showAndHidePinDialog: Boolean){
        uiState.value = uiState.value.copy(
            showAndHidePinDialog = showAndHidePinDialog
        )
    }

    private fun onPin(pin: String){
        uiState.value = uiState.value.copy(
            pin = pin
        )
    }

    private fun onMessageDialog(messageDialogContent: String, messageDialogShowAndHide: Boolean){
        uiState.value = uiState.value.copy(
            messageDialogContent = messageDialogContent,
            messageDialogShowAndHide = messageDialogShowAndHide
        )
    }

    fun airtimeEventHandler(airtimeEvent: AirtimeEvent) {
        when (airtimeEvent) {
            is AirtimeEvent.OnExpandNetworkWidget ->{
                onExpandNetworkWidget(airtimeEvent.expanded)
            }

            is AirtimeEvent.OnselectedNetwork->{
                onselectedNetwork(airtimeEvent.selectedNetwork)
            }

            is AirtimeEvent.OnselectNetworkImage->{
                onselectNetworkImage(airtimeEvent.selectNetworkImage)
            }

            is AirtimeEvent.OnPhoneNumber->{
                onPhoneNumber(airtimeEvent.phoneNumber)
            }

            is AirtimeEvent.OnAmount->{
                onAmount(airtimeEvent.amount)
            }

            is AirtimeEvent.OnContinue->{
                onContinue()
            }

            is AirtimeEvent.OnshowAndHidePinDialog->{
                onshowAndHidePinDialog(airtimeEvent.showAndHidePinDialog)
            }

            is AirtimeEvent.OnPin->{
                onPin(airtimeEvent.pin)
            }

            is AirtimeEvent.MessageDialog->{
                onMessageDialog(airtimeEvent.message, airtimeEvent.viewStatus)
            }
        }
    }

    init {
        viewModelScope.launch {
            val billProduct = repo.getBillingProduct(repo.customerProfile().terminalId, repo.customerProfile().sessionId, "airtime")
            val decryptedAirtime = EncryptionUtil().isDecryption(billProduct.body()!!.data, repo.customerProfile().sessionId)
            val getAirtimeListOfVariousNetwork: List<VariousAirtime> = Constant.gson.fromJson(decryptedAirtime, Array<VariousAirtime>::class.java).toList()
            onVariousNetworkAirtimeList(getAirtimeListOfVariousNetwork)
        }
    }


}