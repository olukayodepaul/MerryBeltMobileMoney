package com.example.merrybeltmobilemoney.ui.home.payorbuy.buydata.buydata_domain


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merrybeltmobilemoney.Application
import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.ui.home.payorbuy.buydata.buydata_data.DataEvent
import com.example.merrybeltmobilemoney.ui.home.payorbuy.buydata.buydata_data.DataState
import com.example.merrybeltmobilemoney.ui.home.payorbuy.buydata.buydata_data.VariousNetwork
import com.example.merrybeltmobilemoney.util.Constant
import com.example.merrybeltmobilemoney.util.EncryptionUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BuyDataViewModel @Inject constructor(
    private val repo: MerryBeltApiRepository,
    private val appContext: Application
) : ViewModel() {

    var uiState = MutableStateFlow(DataState())

    private fun onVariousNetworkAirtimeList(difAirTimeNetwork: List<VariousNetwork>) {
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

    private fun onselectedNetwork(selectedNetwork: String, index: Int) {

        uiState.value = uiState.value.copy(
            selectedNetwork = selectedNetwork,
            selectedIndex = index,
            dataPlanExpandNetworkWidget = false,
            dataPlanSelect = "",
            id = -1
        )

        val isDataPlan: List<VariousNetwork> = uiState.value.difAirTimeNetwork

        uiState.value = uiState.value.copy(
            dataPlanList = isDataPlan[index].plan!!
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

    private fun onContinue() {

        if(uiState.value.phoneNumber=="" && uiState.value.selectedNetwork == "" && uiState.value.id == -1){
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

    private fun OnDataPlanExpandNetworkWidget(dataPlanExpandNetworkWidget: Boolean){
        uiState.value = uiState.value.copy(
            dataPlanExpandNetworkWidget = dataPlanExpandNetworkWidget
        )
    }

    private fun onDataPlanSelect(dataPlanSelect: String, id: Int){
        uiState.value = uiState.value.copy(
            dataPlanSelect = dataPlanSelect,
            id = id
        )
    }

    private fun onPin(pin: String){
        uiState.value = uiState.value.copy(
            pin = pin
        )
    }

    private fun onshowAndHidePinDialog(showAndHidePinDialog: Boolean){
        uiState.value = uiState.value.copy(
            showAndHidePinDialog = showAndHidePinDialog
        )
    }

    private fun onMessageDialog(messageDialogContent: String, messageDialogShowAndHide: Boolean){
        uiState.value = uiState.value.copy(
            messageDialogContent = messageDialogContent,
            messageDialogShowAndHide = messageDialogShowAndHide
        )
    }

    fun dataEventHandler(dataEvent: DataEvent) {
        when(dataEvent) {

            is DataEvent.OnselectedNetwork->{
                onselectedNetwork(dataEvent.selectedNetwork, dataEvent.selectedIndex)
            }
            is DataEvent.OnExpandNetworkWidget ->{
                onExpandNetworkWidget(dataEvent.expanded)
            }

            is DataEvent.OnDataPlanExpandNetworkWidget ->{
                OnDataPlanExpandNetworkWidget(dataEvent.dataPlanExpandNetworkWidget)
            }

            is DataEvent.OnDataPlanSelect ->{
                onDataPlanSelect(dataEvent.dataPlanSelect, dataEvent.id)
            }

            is DataEvent.OnPin->{
                onPin(dataEvent.pin)
            }

            is DataEvent.OnshowAndHidePinDialog->{
                onshowAndHidePinDialog(dataEvent.showAndHidePinDialog)
            }

            is DataEvent.OnselectNetworkImage->{
                onselectNetworkImage(dataEvent.selectNetworkImage)
            }

            is DataEvent.OnPhoneNumber->{
                onPhoneNumber(dataEvent.phoneNumber)
            }

            is DataEvent.OnAmount->{
                onAmount(dataEvent.amount)
            }

            is DataEvent.OnContinue->{
                onContinue()
            }

            is DataEvent.MessageDialog->{
                onMessageDialog(dataEvent.message, dataEvent.viewStatus)
            }
        }
    }

    init {
        viewModelScope.launch {
            val billProduct = repo.getBillingProduct(repo.customerProfile().terminalId, repo.customerProfile().sessionId, "data")
            val decryptedAirtime = EncryptionUtil().isDecryption(billProduct.body()!!.data, repo.customerProfile().sessionId)
            val getAirtimeListOfVariousNetwork: List<VariousNetwork> = Constant.gson.fromJson(decryptedAirtime, Array<VariousNetwork>::class.java).toList()
            onVariousNetworkAirtimeList(getAirtimeListOfVariousNetwork)
        }
    }
}