package com.example.merrybeltmobilemoney.ui.home.presenters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.ui.home.bill_airtime_payment.bill_airtime_data.*
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.AllBanks
import com.example.merrybeltmobilemoney.util.Constant
import com.example.merrybeltmobilemoney.util.EncryptionUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BillAndAirtimeViewModel @Inject constructor(private val repo: MerryBeltApiRepository) :
    ViewModel() {

    var uiState = MutableStateFlow(BillAirtimeEventState())

    private fun onPaymentList(list_Of_PaymentCategory: List<PaymentCategory>) {
        uiState.value = uiState.value.copy(
            list_Of_PaymentCategory = list_Of_PaymentCategory,
        )
    }

    private fun onSpecimenText(specimen: String) {
        uiState.value = uiState.value.copy(
            specimen = specimen,
        )
    }

    private fun onExpanded(expanded: Boolean) {
        uiState.value = uiState.value.copy(
            expanded = expanded,
        )
    }

    private fun onCategoryId(categoryId: Int) {
        uiState.value = uiState.value.copy(
            categoryId = categoryId,
        )
    }

    private fun onNetworkList(networkList: List<DataCategory>) {
        uiState.value = uiState.value.copy(
            networkList = networkList,
        )
    }

    private fun onNtSpecimenText(ntSpecimen: String) {
        uiState.value = uiState.value.copy(
            ntSpecimen = ntSpecimen,
        )
    }

    private fun onNtExpanded(ntExpanded: Boolean) {
        uiState.value = uiState.value.copy(
            ntExpanded = ntExpanded,
        )
    }

    fun billAirtimeEventHandler(billAirtimeEvent: BillAirtimeEvent) {
        when(billAirtimeEvent) {
            is BillAirtimeEvent.OnSpecimenText->{
                onSpecimenText(billAirtimeEvent.specimen)
            }
            is BillAirtimeEvent.OnExpanded->{
                onExpanded(billAirtimeEvent.expanded)
            }
            is BillAirtimeEvent.OnCategoryId->{
                onCategoryId(billAirtimeEvent.categoryId)
            }
            is BillAirtimeEvent.OnNtSpecimen->{
                onNtSpecimenText(billAirtimeEvent.ntSpecimen)
            }
            is BillAirtimeEvent.OnNtExpanded->{
                onNtExpanded(billAirtimeEvent.ntExpanded)
            }
        }
    }

    init {

        viewModelScope.launch {

            val billProduct = repo.getBillingProduct(repo.customerProfile().terminalId, repo.customerProfile().sessionId, "data")
            val billProductRes = EncryptionUtil().isDecryption(billProduct.body()!!.data, repo.customerProfile().sessionId)
            val getDataList: List<DataCategory> = Constant.gson.fromJson(billProductRes, Array<DataCategory>::class.java).toList()
            onNetworkList(getDataList)

        }

        val category = ArrayList<PaymentCategory>()
        category.add(PaymentCategory(id = 1, category = "Buy Airtime"))
        category.add(PaymentCategory(id = 2, category = "Buy Data"))
        category.add(PaymentCategory(id = 3, category = "Pay for Cable Tv"))
        category.add(PaymentCategory(id = 4, category = "Pay For Internet"))
        onPaymentList(category)
    }
}