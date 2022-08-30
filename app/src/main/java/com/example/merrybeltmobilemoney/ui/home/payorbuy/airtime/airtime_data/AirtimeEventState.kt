package com.example.merrybeltmobilemoney.ui.home.payorbuy.airtime.airtime_data


data class AirtimeState(
    val airtimeList: List<AirtimeProductList> = emptyList(),
    val airtimeProductLoader: Boolean = false,
    val airtimeProductSelected: String = "",
    val airtimeProductExpanded: Boolean = false,
    val airtimeProductImage: String = "",
    val airtimeProductCategory: String = "",
    val phoneNumber: String = "",
    val amount: String = ""
)

sealed class AirtimeEvent {
    class OnAirtimeProductExpanded(val airtimeProductExpanded: Boolean) : AirtimeEvent()
    class OnAirtimeProductSelected(val airtimeProductSelected: String, val airtimeProductImage: String, val airtimeProductCategory: String) : AirtimeEvent()
    class OnPhoneNumber(val phoneNumber: String) : AirtimeEvent()
    class OnAmount(val amount: String) : AirtimeEvent()
}
