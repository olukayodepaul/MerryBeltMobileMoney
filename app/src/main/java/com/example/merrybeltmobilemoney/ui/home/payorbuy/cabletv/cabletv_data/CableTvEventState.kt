package com.example.merrybeltmobilemoney.ui.home.payorbuy.cabletv.cabletv_data



data class CableTvState(
    val cableTvList: List<CabletvProduct> = emptyList(),
    val cableTvProductLoader: Boolean = false,
    val cableTvProductSelected: String = "",
    val cableTvProductExpanded: Boolean = false,
    val cableTvProductImage: String = "",
    val cableTvProductCategory: String = "",
    val cableTvIndex:Int = -1,

    //this is for plan list,
    val cableTvProductPlan : List<CabletvCategory> = emptyList(),
    val cableTvProductSelectedPlan: String = "",
    val cableTvProductExpandedPlan: Boolean = false,
    val cableTvProductPlanId: Int = 0,
    val cableTvProductPlanPrice: String = "",

    //this is the entring field
    val smartCardNumber: String = "",
    val phoneNumber: String = ""
)

sealed class CableTvEvent {
    class OnCableTvProductExpanded(val cableTvProductExpanded: Boolean) : CableTvEvent()
    class OnCableTvProductSelected(val cableTvProductSelected: String, val cableTvIndex: Int, val cableTvProductCategory: String, val cableTvProductImage: String) : CableTvEvent()
    class OnCableTvProductExpandedPlan(val cableTvProductExpandedPlan: Boolean) : CableTvEvent()
    class OnCableTvProductSelectedPlan(val cableTvProductSelectedPlan: String ,  val cableTvProductPlanId: Int, val cableTvProductPlanPrice: String): CableTvEvent()
    class OnSmartCardNumber(val smartCardNumber: String) : CableTvEvent()
    class OnPhoneNumber(val phoneNumber: String) : CableTvEvent()
}

