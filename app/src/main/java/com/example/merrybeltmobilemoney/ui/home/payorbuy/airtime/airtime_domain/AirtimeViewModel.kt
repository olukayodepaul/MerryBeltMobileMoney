package com.example.merrybeltmobilemoney.ui.home.payorbuy.airtime.airtime_domain


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merrybeltmobilemoney.Application
import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.ui.home.payorbuy.airtime.airtime_data.AirtimeEvent
import com.example.merrybeltmobilemoney.ui.home.payorbuy.airtime.airtime_data.AirtimeProductList
import com.example.merrybeltmobilemoney.ui.home.payorbuy.airtime.airtime_data.AirtimeState
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

    private fun airtimeProduct(airtimeList: List<AirtimeProductList>) {
        uiState.value = uiState.value.copy(
            airtimeList = airtimeList
        )
    }

    private fun onAirtimeProductExpanded(airtimeProductExpanded: Boolean) {
        uiState.value = uiState.value.copy(
            airtimeProductExpanded = airtimeProductExpanded
        )
    }

    private fun onAirtimeProductSelected(airtimeProductSelected: String, airtimeProductImage: String, airtimeProductCategory: String){
        uiState.value = uiState.value.copy(
            airtimeProductSelected = airtimeProductSelected,
            airtimeProductImage = airtimeProductImage,
            airtimeProductCategory = airtimeProductCategory
        )
    }

    private fun onPhoneNumber(phoneNumber: String) {
        uiState.value = uiState.value.copy(
            phoneNumber = phoneNumber
        )
    }

    private fun onAmount(amount: String) {
        uiState.value = uiState.value.copy(
            amount = amount
        )
    }

    fun airtimeEventHandler(airtimeEvent: AirtimeEvent) {
        when (airtimeEvent) {
            is AirtimeEvent.OnAirtimeProductExpanded->{
                onAirtimeProductExpanded(airtimeEvent.airtimeProductExpanded)
            }
            is AirtimeEvent.OnAirtimeProductSelected->{
                onAirtimeProductSelected(airtimeEvent.airtimeProductSelected, airtimeEvent.airtimeProductImage, airtimeEvent.airtimeProductCategory)
            }
            is AirtimeEvent.OnPhoneNumber->{
                onPhoneNumber(airtimeEvent.phoneNumber)
            }
            is AirtimeEvent.OnAmount->{
                onAmount(airtimeEvent.amount)
            }
        }
    }

    init {
        try{
            viewModelScope.launch {
                val billProduct = repo.getBillingProduct(repo.customerProfile().terminalId, repo.customerProfile().sessionId, "airtime")
                val decryptedAirtime = EncryptionUtil().isDecryption(billProduct.body()!!.data, repo.customerProfile().sessionId)
                val getAirtimeListOfVariousNetwork: List<AirtimeProductList> = Constant.gson.fromJson(decryptedAirtime, Array<AirtimeProductList>::class.java).toList()
                airtimeProduct(getAirtimeListOfVariousNetwork)
            }
        }catch (e:Throwable){}

        //hide this component here
    }


}