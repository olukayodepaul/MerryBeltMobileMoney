package com.example.merrybeltmobilemoney.ui.home.payorbuy.buydata.buydata_data


data class DataState(
    val dataList: List<DataProductList> = emptyList(),
    val dataProductLoader: Boolean = false,
    val dataProductSelected: String = "",
    val dataProductExpanded: Boolean = false,
    val dataProductImage: String = "",
    val dataProductCategory: String = "",
    val dataIndex:Int = -1,

    //this is for plan list,
    val dataProductPlan : List<DataPlan> = emptyList(),
    val dataProductSelectedPlan: String = "",
    val dataProductExpandedPlan: Boolean = false,
    val dataProductPlanId: Int = 0,
    val dataProductPlanPrice: String = "",

    val phoneNumber: String = "",
    val amount: String = ""
)

sealed class DataEvent {
    class OnDataProductExpanded(val dataProductExpanded: Boolean) : DataEvent()
    class OnDataProductSelected(val dataProductSelected: String, val dataProductImage: String, val dataProductCategory: String, val dataIndex: Int) : DataEvent()
    class OnDataProductExpandedPlan(val dataProductExpandedPlan: Boolean) : DataEvent()
    class OnDataProductSelectedPlan(val dataProductSelectedPlan: String ,  val dataProductPlanId: Int, val dataProductPlanPrice: String): DataEvent()
    class OnPhoneNumber(val phoneNumber: String) : DataEvent()
}
