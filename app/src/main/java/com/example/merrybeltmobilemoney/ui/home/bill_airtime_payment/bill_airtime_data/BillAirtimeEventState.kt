package com.example.merrybeltmobilemoney.ui.home.bill_airtime_payment.bill_airtime_data


data class BillAirtimeEventState (
    val list_Of_PaymentCategory: List<PaymentCategory> = emptyList(),
    val specimen: String = "",
    val expanded: Boolean = false,
    val categoryId: Int = 0,

    val networkList: List<DataCategory> = emptyList(),
    val ntSpecimen: String = "",
    val ntExpanded: Boolean = false,
    val index: Int = -1,

    val dataPlanList: List<DataPlan> = emptyList(),
    val dtSetText: String = "",
    val dtExpanded: Boolean = false,

    val dataAmount: String = "",
    val dataPhoneNumber: String = "",

    val onChangeCategory: Int = 0,
    val loader: Boolean = false,



)

sealed class BillAirtimeEvent {
    //category set
    class OnExpanded(val expanded: Boolean) : BillAirtimeEvent()
    class OnSpecimenText(val specimen:  String) : BillAirtimeEvent()
    class OnCategoryId(val categoryId:  Int) : BillAirtimeEvent()

    //networkForData set
    class OnNtSpecimen(val ntSpecimen: String, val index : Int) : BillAirtimeEvent()
    class OnNtExpanded(val ntExpanded:  Boolean) : BillAirtimeEvent()

    //data plan set
    class OnDtSetText(val dtSetText: String) : BillAirtimeEvent()
    class OnDtExpanded(val dtExpanded:  Boolean) : BillAirtimeEvent()

    //data Amount and Phone Number Event
    class OnDataAmount(val dataAmount: String) : BillAirtimeEvent()
    class OnDataPhoneNumber(val dataPhoneNumber: String) : BillAirtimeEvent()

    //OnChangeCategory
    class OnChangeCategory(val onChangeCategory: Int) : BillAirtimeEvent()

    class OnLoader(val loader: Boolean) : BillAirtimeEvent()


}