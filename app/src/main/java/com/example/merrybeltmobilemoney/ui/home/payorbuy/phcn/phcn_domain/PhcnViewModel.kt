package com.example.merrybeltmobilemoney.ui.home.payorbuy.phcn.phcn_domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merrybeltmobilemoney.Application
import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.ui.home.payorbuy.phcn.phcn_data.*
import com.example.merrybeltmobilemoney.util.Constant
import com.example.merrybeltmobilemoney.util.EncryptionUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhcnViewModel @Inject constructor(
    private val repo: MerryBeltApiRepository,
    private val appContext: Application
) : ViewModel() {


    var uiState = MutableStateFlow(PhcnState())

    private fun phcnProduct(productList: List<PhcnProduct>) {
        uiState.value = uiState.value.copy(
            //showAndHideLoader = true,
            productList = productList
        )
    }

    private fun onProductSelected(productSelected: String){
        uiState.value = uiState.value.copy(
            productSelected = productSelected
        )
    }

    private fun onProductImage(productImage: String){
        uiState.value = uiState.value.copy(
            productImage = productImage
        )
    }

    private fun onProductExpanded(productExpanded: Boolean){
        uiState.value = uiState.value.copy(
            productExpanded = productExpanded
        )
    }

    private fun onProductCategory(productCategory: String){
        uiState.value = uiState.value.copy(
            productCategory = productCategory
        )
    }

    private fun isMeterType(meterType: List<MeterType>) {
        uiState.value = uiState.value.copy(
            meterType = meterType
        )
    }


    fun phcnEventHandler(phcnEvent: PhcnEvent) {
        when (phcnEvent) {

            is PhcnEvent.OnProductSelected->{
                onProductSelected(phcnEvent.productSelected)
            }

            is PhcnEvent.OnProductImage->{
                onProductImage(phcnEvent.productImage)
            }

            is PhcnEvent.OnProductExpanded->{
                onProductExpanded(phcnEvent.productExpanded)
            }

            is PhcnEvent.OnProductCategory->{
                onProductCategory(phcnEvent.productCategory)
            }

        }
    }


    init {

        val category = ArrayList<MeterType>()
        category.add(MeterType(type = "POSTPAID"))
        category.add(MeterType(type = "PREPAID"))
        isMeterType(category)

        viewModelScope.launch {
            val billProduct = repo.getBillingProduct(repo.customerProfile().terminalId, repo.customerProfile().sessionId, "phcn")
            val decryptedAirtime = EncryptionUtil().isDecryption(billProduct.body()!!.data, repo.customerProfile().sessionId)
            val getAirtimeListOfVariousNetwork: List<PhcnProduct> = Constant.gson.fromJson(decryptedAirtime, Array<PhcnProduct>::class.java).toList()
            phcnProduct(getAirtimeListOfVariousNetwork)
        }

    }
}