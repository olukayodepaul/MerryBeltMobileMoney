package com.example.merrybeltmobilemoney.ui.home.payorbuy.buydata.buydata_domain


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merrybeltmobilemoney.Application
import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.ui.home.payorbuy.buydata.buydata_data.DataEvent
import com.example.merrybeltmobilemoney.ui.home.payorbuy.buydata.buydata_data.DataProductList
import com.example.merrybeltmobilemoney.ui.home.payorbuy.buydata.buydata_data.DataState
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

    private fun onDataProduct(dataList: List<DataProductList>) {
        uiState.value = uiState.value.copy(
            //showAndHideLoader = true,
            dataList = dataList
        )
    }

    private fun onDataProductExpanded(dataProductExpanded: Boolean) {
        uiState.value = uiState.value.copy(
            dataProductExpanded = dataProductExpanded
        )
    }

    private fun onDataProductSelected(dataProductSelected: String, dataProductImage: String, dataProductCategory: String, dataIndex: Int) {
        val isDataPlan: List<DataProductList> = uiState.value.dataList
        uiState.value = uiState.value.copy (
            amount = "",
            dataProductSelectedPlan = "",
            dataProductExpandedPlan = false,
            dataProductPlanId = 0,
            dataProductPlanPrice = "",
            dataProductSelected = dataProductSelected,
            dataProductImage = dataProductImage,
            dataProductCategory = dataProductCategory,
            dataProductPlan = isDataPlan[dataIndex].plan!!
        )
    }

    private fun onDataProductExpandedPlan(dataProductExpandedPlan: Boolean) {
        uiState.value = uiState.value.copy(
            dataProductExpandedPlan = dataProductExpandedPlan
        )
    }

    private fun onDataProductSelectedPlan(dataProductSelectedPlan: String ,  dataProductPlanId: Int, dataProductPlanPrice: String) {
        uiState.value = uiState.value.copy(
            dataProductSelectedPlan = dataProductSelectedPlan,
            dataProductPlanId = dataProductPlanId,
            dataProductPlanPrice = dataProductPlanPrice,
            amount = dataProductPlanPrice
        )
    }

    private fun onPhoneNumber(phoneNumber: String) {
        uiState.value = uiState.value.copy(
            phoneNumber = phoneNumber
        )
    }

    fun dataEventHandler(dataEvent: DataEvent) {
        when(dataEvent) {
            is DataEvent.OnDataProductExpanded->{
                onDataProductExpanded(dataEvent.dataProductExpanded)
            }
            is DataEvent.OnDataProductSelected->{
                onDataProductSelected(dataEvent.dataProductSelected, dataEvent.dataProductImage, dataEvent.dataProductCategory, dataEvent.dataIndex)
            }
            is DataEvent.OnDataProductExpandedPlan->{
                onDataProductExpandedPlan(dataEvent.dataProductExpandedPlan)
            }
            is DataEvent.OnDataProductSelectedPlan->{
                onDataProductSelectedPlan(dataEvent.dataProductSelectedPlan, dataEvent.dataProductPlanId, dataEvent.dataProductPlanPrice)
            }
            is DataEvent.OnPhoneNumber->{
                onPhoneNumber(dataEvent.phoneNumber)
            }
        }
    }

    init {
        viewModelScope.launch {
            try{
                val billProduct = repo.getBillingProduct(repo.customerProfile().terminalId, repo.customerProfile().sessionId, "data")
                val decryptedAirtime = EncryptionUtil().isDecryption(billProduct.body()!!.data, repo.customerProfile().sessionId)
                val getAirtimeListOfVariousNetwork: List<DataProductList> = Constant.gson.fromJson(decryptedAirtime, Array<DataProductList>::class.java).toList()
                onDataProduct(getAirtimeListOfVariousNetwork)
            }catch (e:Throwable){ }
        }
    }
}