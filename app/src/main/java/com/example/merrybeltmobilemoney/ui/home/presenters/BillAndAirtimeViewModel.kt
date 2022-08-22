package com.example.merrybeltmobilemoney.ui.home.presenters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.ui.home.bill_airtime_payment.bill_airtime_data.BillAirtimeEvent
import com.example.merrybeltmobilemoney.ui.home.bill_airtime_payment.bill_airtime_data.BillAirtimeEventState
import com.example.merrybeltmobilemoney.ui.home.bill_airtime_payment.bill_airtime_data.NetworkList
import com.example.merrybeltmobilemoney.ui.home.bill_airtime_payment.bill_airtime_data.PaymentCategory
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

    private fun onNetworkList(networkList: List<NetworkList>) {
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
            Log.d("epoi", "${billProduct.body()}")
        }
        val category = ArrayList<PaymentCategory>()
        category.add(PaymentCategory(id = 1, category = "Buy Airtime"))
        category.add(PaymentCategory(id = 2, category = "Buy Data"))
        category.add(PaymentCategory(id = 3, category = "Pay for Cable Tv"))
        category.add(PaymentCategory(id = 4, category = "Pay For Internet"))
        onPaymentList(category)

        val networkList = ArrayList<NetworkList>()
        networkList.add(NetworkList(id = 1, category = "AIRTEL"))
        networkList.add(NetworkList(id = 2, category = "MTN"))
        networkList.add(NetworkList(id = 3, category = "ETISALAT"))
        networkList.add(NetworkList(id = 4, category = "GLO"))
        onNetworkList(networkList)



    }
}