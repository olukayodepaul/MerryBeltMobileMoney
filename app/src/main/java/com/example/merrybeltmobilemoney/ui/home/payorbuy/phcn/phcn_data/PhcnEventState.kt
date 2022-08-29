package com.example.merrybeltmobilemoney.ui.home.payorbuy.phcn.phcn_data



data class PhcnState(
    val productList: List<PhcnProduct> = emptyList(),
    val productLoader: Boolean = false,
    val productSelected: String = "",
    val productExpanded: Boolean = false,
    val productImage: String = "",
    val productCategory: String = "",

    val phoneNumber: String = "",
    val meterNumber: String = "",
    val amount: String = "",

    val meterType: List<MeterType> = emptyList(),
    val meterTypeSelected: String = "",
    val meterTypeExpanded: Boolean = false,

)


sealed class PhcnEvent {

    class OnProductExpanded(val productExpanded: Boolean) : PhcnEvent()
    class OnProductSelected(val productSelected: String) : PhcnEvent()
    class OnProductImage(val productImage: String) : PhcnEvent()
    class OnProductCategory(val productCategory: String) : PhcnEvent()


    class OnPhoneNumber(val phoneNumber: String) : PhcnEvent()
    class OnMeterNumber(val meterNumber: String) : PhcnEvent()
    class OnAmount(val amount: String) : PhcnEvent()
    class OnMeterTypeSelected(val meterTypeSelected: String) : PhcnEvent()
    class OnMeterTypeExpanded(val meterTypeExpanded: Boolean) : PhcnEvent()
    object OnContinue: PhcnEvent()

}

