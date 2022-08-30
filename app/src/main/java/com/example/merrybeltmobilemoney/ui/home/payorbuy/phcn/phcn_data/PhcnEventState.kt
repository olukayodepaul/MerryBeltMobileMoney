package com.example.merrybeltmobilemoney.ui.home.payorbuy.phcn.phcn_data

data class PhcnState(

    val phcnTvList: List<PhcnProductList> = emptyList(),
    val phcnTvProductLoader: Boolean = false,
    val phcnTvProductSelected: String = "",
    val phcnTvProductExpanded: Boolean = false,
    val phcnTvProductImage: String = "",
    val phcnTvProductCategory: String = "",


    val phcnMeterTypeList: List<MeterType> = emptyList(),
    val phcnTvProductSelectedMeterType: String = "",
    val phcnTvProductExpandedMeterType: Boolean = false,

    //this is the entring field
    val meterNumber: String = "",
    val phoneNumber: String = "",
    val amount: String = ""

)


sealed class PhcnEvent {
    class OnPhcnTvProductExpanded(val phcnTvProductExpanded: Boolean) : PhcnEvent()
    class OnPhcnTvProductSelected(val phcnTvProductSelected: String, val phcnTvProductCategory: String, val phcnTvProductImage: String) : PhcnEvent()
    class OnPhcnTvProductSelectedMeterType(val phcnTvProductSelectedMeterType: String) : PhcnEvent()
    class OnPhcnTvProductExpandedMeterType(val phcnTvProductExpandedMeterType: Boolean) : PhcnEvent()
    class OnMeterNumber(val meterNumber: String) : PhcnEvent()
    class OnPhoneNumber(val phoneNumber: String) : PhcnEvent()
    class OnAmount(val amount: String) : PhcnEvent()
}

