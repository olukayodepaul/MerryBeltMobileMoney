package com.example.merrybeltmobilemoney.ui.home.payorbuy.phcn.phcn_domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merrybeltmobilemoney.Application
import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.ui.home.payorbuy.cabletv.cabletv_data.CableTvEvent
import com.example.merrybeltmobilemoney.ui.home.payorbuy.phcn.phcn_data.MeterType
import com.example.merrybeltmobilemoney.ui.home.payorbuy.phcn.phcn_data.PhcnEvent
import com.example.merrybeltmobilemoney.ui.home.payorbuy.phcn.phcn_data.PhcnProductList
import com.example.merrybeltmobilemoney.ui.home.payorbuy.phcn.phcn_data.PhcnState
import com.example.merrybeltmobilemoney.util.Constant
import com.example.merrybeltmobilemoney.util.EncryptionUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhcnViewModel @Inject constructor(
    private val repo: MerryBeltApiRepository,
    private val appContext: Application
) : ViewModel() {


    var uiState = MutableStateFlow(PhcnState())

    private fun phcnProduct(phcnTvList: List<PhcnProductList>) {
        uiState.update {
            it.copy(
                phcnTvList = phcnTvList
            )
        }
    }

    private fun onPhcnTvProductExpanded(phcnTvProductExpanded: Boolean) {
        uiState.update {
            it.copy(phcnTvProductExpanded = phcnTvProductExpanded)
        }
    }

    private fun onPhcnTvProductSelected(phcnTvProductSelected: String, phcnTvProductCategory: String, phcnTvProductImage: String){
        uiState.update {
            it.copy(
                phcnTvProductSelected = phcnTvProductSelected,
                phcnTvProductCategory = phcnTvProductCategory,
                phcnTvProductImage = phcnTvProductImage
            )
        }
    }

    private fun onPhcnMeterType(phcnMeterTypeList: List<MeterType>) {
        uiState.update {
            it.copy(phcnMeterTypeList = phcnMeterTypeList)
        }
    }

    private fun onPhcnTvProductExpandedMeterType(phcnTvProductExpandedMeterType: Boolean) {
        uiState.update {
            it.copy(phcnTvProductExpandedMeterType = phcnTvProductExpandedMeterType)
        }
    }

    private fun onPhcnTvProductSelectedMeterType(phcnTvProductSelectedMeterType: String) {
        uiState.update {
            it.copy(phcnTvProductSelectedMeterType = phcnTvProductSelectedMeterType)
        }
    }

    private fun onMeterNumber(meterNumber: String){
        uiState.update {
            it.copy(meterNumber = meterNumber)
        }
    }

    private fun onPhoneNumber(phoneNumber: String){
        uiState.update {
            it.copy(phoneNumber = phoneNumber)
        }
    }

    private fun onAmount(amount: String){
        uiState.update {
            it.copy(amount = amount)
        }
    }

    fun phcnEventHandler(phcnEvent: PhcnEvent) {
        when (phcnEvent) {
            is PhcnEvent.OnPhcnTvProductExpanded->{
                onPhcnTvProductExpanded(phcnEvent.phcnTvProductExpanded)
            }
            is PhcnEvent.OnPhcnTvProductSelected->{
                onPhcnTvProductSelected(phcnEvent.phcnTvProductSelected, phcnEvent.phcnTvProductCategory, phcnEvent.phcnTvProductImage)
            }
            is PhcnEvent.OnPhcnTvProductExpandedMeterType->{
                onPhcnTvProductExpandedMeterType(phcnEvent.phcnTvProductExpandedMeterType)
            }
            is PhcnEvent.OnPhcnTvProductSelectedMeterType->{
                onPhcnTvProductSelectedMeterType(phcnEvent.phcnTvProductSelectedMeterType)
            }
            is PhcnEvent.OnMeterNumber->{
                onMeterNumber(phcnEvent.meterNumber)
            }
            is PhcnEvent.OnPhoneNumber->{
                onPhoneNumber(phcnEvent.phoneNumber)
            }
            is PhcnEvent.OnAmount->{
                onAmount(phcnEvent.amount)
            }
        }
    }


    init {

        viewModelScope.launch {

            val category = ArrayList<MeterType>()
            category.add(MeterType(type = "POSTPAID"))
            category.add(MeterType(type = "PREPAID"))
            onPhcnMeterType(category)

            try{
                val billProduct = repo.getBillingProduct(repo.customerProfile().terminalId, repo.customerProfile().sessionId, "phcn")
                val decryptedAirtime = EncryptionUtil().isDecryption(billProduct.body()!!.data, repo.customerProfile().sessionId)
                val getAirtimeListOfVariousNetwork: List<PhcnProductList> = Constant.gson.fromJson(decryptedAirtime, Array<PhcnProductList>::class.java).toList()
                phcnProduct(getAirtimeListOfVariousNetwork)
            }catch (e:Throwable){}
        }

    }
}