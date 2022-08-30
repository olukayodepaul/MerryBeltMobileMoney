package com.example.merrybeltmobilemoney.ui.home.payorbuy.cabletv.cabletv_domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merrybeltmobilemoney.Application
import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.ui.home.payorbuy.cabletv.cabletv_data.CableTvEvent
import com.example.merrybeltmobilemoney.ui.home.payorbuy.cabletv.cabletv_data.CableTvState
import com.example.merrybeltmobilemoney.ui.home.payorbuy.cabletv.cabletv_data.CabletvCategory
import com.example.merrybeltmobilemoney.ui.home.payorbuy.cabletv.cabletv_data.CabletvProduct
import com.example.merrybeltmobilemoney.util.Constant
import com.example.merrybeltmobilemoney.util.EncryptionUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CableTvViewModel @Inject constructor(
    private val repo: MerryBeltApiRepository,
    private val appContext: Application
) : ViewModel() {

    var uiState = MutableStateFlow(CableTvState())

    private fun cableTvProduct(cableTbList: List<CabletvProduct>) {
        uiState.value = uiState.value.copy(
            //showAndHideLoader = true,
            cableTvList = cableTbList
        )
    }

    private fun onCableTvProductExpanded(cableTvProductExpanded: Boolean) {
        uiState.value = uiState.value.copy(
            cableTvProductExpanded = cableTvProductExpanded
        )
    }

    private fun onCableTvProductSelected(cableTvProductSelected: String, cableTvIndex: Int, cableTvProductCategory: String, cableTvProductImage: String) {

        val isDataPlan: List<CabletvProduct> = uiState.value.cableTvList

        uiState.value = uiState.value.copy(
            cableTvProductSelectedPlan = "",
            cableTvProductExpandedPlan = false,
            cableTvProductPlanId = 0,
            cableTvProductPlanPrice = "",
            cableTvProductSelected = cableTvProductSelected,
            cableTvIndex = cableTvIndex,
            cableTvProductCategory = cableTvProductCategory,
            cableTvProductImage = cableTvProductImage,
            cableTvProductPlan = isDataPlan[cableTvIndex].plan!!
        )
    }

    private fun onCableTvProductExpandedPlan(cableTvProductExpandedPlan: Boolean) {
        uiState.value = uiState.value.copy(
            cableTvProductExpandedPlan = cableTvProductExpandedPlan
        )
    }

    private fun onCableTvProductSelectedPlan(cableTvProductSelectedPlan: String , cableTvProductPlanId: Int, cableTvProductPlanPrice: String) {
        uiState.value = uiState.value.copy(
            cableTvProductSelectedPlan = cableTvProductSelectedPlan,
            cableTvProductPlanId = cableTvProductPlanId,
            cableTvProductPlanPrice = cableTvProductPlanPrice
        )
    }

    private fun onSmartCardNumber(smartCardNumber: String){
        uiState.value = uiState.value.copy(
            smartCardNumber = smartCardNumber
        )
    }

    private fun onPhoneNumber(phoneNumber: String){
        uiState.value = uiState.value.copy(
            phoneNumber = phoneNumber
        )
    }

    fun cableTvEventHandler(cabletvEvent: CableTvEvent) {
        when (cabletvEvent) {
            is CableTvEvent.OnCableTvProductExpanded->{
                onCableTvProductExpanded(cabletvEvent.cableTvProductExpanded)
            }
            is CableTvEvent.OnCableTvProductSelected->{
                onCableTvProductSelected(cabletvEvent.cableTvProductSelected, cabletvEvent.cableTvIndex, cabletvEvent.cableTvProductCategory, cabletvEvent.cableTvProductImage)
            }
            is CableTvEvent.OnCableTvProductExpandedPlan->{
                onCableTvProductExpandedPlan(cabletvEvent.cableTvProductExpandedPlan)
            }
            is CableTvEvent.OnCableTvProductSelectedPlan->{
                onCableTvProductSelectedPlan(cabletvEvent.cableTvProductSelectedPlan, cabletvEvent.cableTvProductPlanId, cabletvEvent.cableTvProductPlanPrice)
            }
            is CableTvEvent.OnSmartCardNumber->{
                onSmartCardNumber(cabletvEvent.smartCardNumber)
            }
            is CableTvEvent.OnPhoneNumber->{
                onPhoneNumber(cabletvEvent.phoneNumber)
            }
        }
    }

    init {
        viewModelScope.launch {
            val billProduct = repo.getBillingProduct(repo.customerProfile().terminalId, repo.customerProfile().sessionId, "cable_tv")
            val decryptedAirtime = EncryptionUtil().isDecryption(billProduct.body()!!.data, repo.customerProfile().sessionId)
            val getAirtimeListOfVariousNetwork: List<CabletvProduct> = Constant.gson.fromJson(decryptedAirtime, Array<CabletvProduct>::class.java).toList()
            cableTvProduct(getAirtimeListOfVariousNetwork)
        }
    }

}