package com.example.merrybeltmobilemoney.ui.home.bill_airtime_payment.bill_airtime_data


data class BillAirtimeEventState (
    val list_Of_PaymentCategory: List<PaymentCategory> = emptyList(),
    val specimen: String = "",
    val expanded: Boolean = false,
    val categoryId: Int = 0,
    val networkList: List<DataCategory> = emptyList(),
    val ntSpecimen: String = "",
    val ntExpanded: Boolean = false,
)

sealed class BillAirtimeEvent {
    class OnExpanded(val expanded: Boolean) : BillAirtimeEvent()
    class OnSpecimenText(val specimen:  String) : BillAirtimeEvent()
    class OnCategoryId(val categoryId:  Int) : BillAirtimeEvent()
    class OnNtSpecimen(val ntSpecimen: String) : BillAirtimeEvent()
    class OnNtExpanded(val ntExpanded:  Boolean) : BillAirtimeEvent()
}