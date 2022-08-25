package com.example.merrybeltmobilemoney.ui.home.presenters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.ui.home.bill_airtime_payment.bill_airtime_data.*
import com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data.BillingProducts
import com.example.merrybeltmobilemoney.util.Constant
import com.example.merrybeltmobilemoney.util.EncryptionUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
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
            specimen = specimen
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

    private fun onDataList(networkList: List<DataCategory>) {
        Log.d("TextPhcn", "$networkList")
        uiState.value = uiState.value.copy(
            loader = true,
            networkList = networkList,
        )
    }

    private fun onNtSpecimenText(ntSpecimen: String, index: Int) {

        uiState.value = uiState.value.copy(
            ntSpecimen = ntSpecimen,
            index = index
        )

        val isDataPlan: List<DataCategory> = uiState.value.networkList

        if(uiState.value.categoryId==2 || uiState.value.categoryId==3) {
            val mapDataPlan = isDataPlan[index].plan
            uiState.value = uiState.value.copy(
                dtSetText= "",
                dataPlanList = mapDataPlan!!
            )
        }

    }

    private fun onNtExpanded(ntExpanded: Boolean) {
        uiState.value = uiState.value.copy(
            ntExpanded = ntExpanded,
        )
    }

    private fun onDtSetText(dtSetText: String){
        uiState.value = uiState.value.copy(
            dtSetText = dtSetText
        )
    }

    private fun onDtExpanded(dtExpanded: Boolean) {
        uiState.value = uiState.value.copy(
            dtExpanded = dtExpanded
        )
    }

    private fun onDataAmount(dataAmount: String) {
        uiState.value = uiState.value.copy(
            dataAmount = dataAmount
        )
    }

    private fun onDataPhoneNumber(dataPhoneNumber: String) {
        uiState.value = uiState.value.copy(
            dataPhoneNumber = dataPhoneNumber
        )
    }

    private fun onChangeCategory(id: Int) = viewModelScope.launch {
        when(id){
            1->{
                viewModelScope.launch {
                    val billProduct = repo.getBillingProduct(repo.customerProfile().terminalId, repo.customerProfile().sessionId, "airtime")
                    val billProductRes = EncryptionUtil().isDecryption(billProduct!!.body()!!.data, repo.customerProfile().sessionId)
                    val getDataList: List<DataCategory> = Constant.gson.fromJson(billProductRes, Array<DataCategory>::class.java).toList()
                    onDataList(getDataList)
                }
            }
            2->{
                viewModelScope.launch {
                    val billProduct = repo.getBillingProduct(repo.customerProfile().terminalId, repo.customerProfile().sessionId, "data")
                    val billProductRes = EncryptionUtil().isDecryption(billProduct!!.body()!!.data, repo.customerProfile().sessionId)
                    val getDataList: List<DataCategory> = Constant.gson.fromJson(billProductRes, Array<DataCategory>::class.java).toList()
                    onDataList(getDataList)
                }
            }
            3->{
                viewModelScope.launch {
                    val billProduct = repo.getBillingProduct(repo.customerProfile().terminalId, repo.customerProfile().sessionId, "cable_tv")
                    val billProductRes = EncryptionUtil().isDecryption(billProduct!!.body()!!.data, repo.customerProfile().sessionId)
                    val getDataList: List<DataCategory> = Constant.gson.fromJson(billProductRes, Array<DataCategory>::class.java).toList()
                    onDataList(getDataList)
                }
            }
            4->{
                viewModelScope.launch {
                    val billProduct = repo.getBillingProduct(repo.customerProfile().terminalId, repo.customerProfile().sessionId, "phcn")
                    val billProductRes = EncryptionUtil().isDecryption(billProduct!!.body()!!.data, repo.customerProfile().sessionId)
                    val getDataList: List<DataCategory> = Constant.gson.fromJson(billProductRes, Array<DataCategory>::class.java).toList()
                    onDataList(getDataList)
                }
            }
        }
    }

    private fun onLoader(loader: Boolean){
        uiState.value = uiState.value.copy(
            loader = loader
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
                onNtSpecimenText(billAirtimeEvent.ntSpecimen, billAirtimeEvent.index)
            }
            is BillAirtimeEvent.OnNtExpanded->{
                onNtExpanded(billAirtimeEvent.ntExpanded)
            }
            is BillAirtimeEvent.OnDtSetText->{
                onDtSetText(billAirtimeEvent.dtSetText)
            }
            is BillAirtimeEvent.OnDtExpanded->{
                onDtExpanded(billAirtimeEvent.dtExpanded)
            }
            is BillAirtimeEvent.OnDataAmount->{
                onDataAmount(billAirtimeEvent.dataAmount)
            }
            is BillAirtimeEvent.OnDataPhoneNumber->{
                onDataPhoneNumber(billAirtimeEvent.dataPhoneNumber)
            }
            is BillAirtimeEvent.OnChangeCategory->{
                onChangeCategory(billAirtimeEvent.onChangeCategory)
            }
            is BillAirtimeEvent.OnLoader->{
                onLoader(billAirtimeEvent.loader)
            }
        }
    }

    init {
        val category = ArrayList<PaymentCategory>()
        category.add(PaymentCategory(id = 1, category = "Airtime"))
        category.add(PaymentCategory(id = 2, category = "Mobile Data"))
        category.add(PaymentCategory(id = 3, category = "Cable Tv"))
        category.add(PaymentCategory(id = 4, category = "PHCN"))
        onPaymentList(category)
    }
}