package com.example.merrybeltmobilemoney.ui.home.payorbuy.buydata.buydata_data


data class DataState(
    val difAirTimeNetwork: List<VariousNetwork> = emptyList(),
    val showAndHideLoader: Boolean = false,
    val selectedNetwork: String = "",
    val expandNetworkWidget: Boolean = false,
    val selectedIndex: Int = -1,
    val selectNetworkImage: String = "",
    val amount: String = "",
    val phoneNumber:String = "",
    val showAndHidePinDialog: Boolean = false,
    val pin: String = "",
    val messageDialogTitle: String = "Notification",
    val messageDialogContent: String = "",
    val messageDialogShowAndHide: Boolean = false,

    //data plan
    val dataPlanList: List<DataPlan> = emptyList(),
    val id: Int = -1,
    val dataPlanSelect: String = "",
    val dataPlanExpandNetworkWidget: Boolean = false,


)

sealed class DataEvent {

    class OnExpandNetworkWidget(val expanded: Boolean) : DataEvent()
    class OnselectedNetwork(val selectedNetwork: String, val selectedIndex: Int) : DataEvent()
    class OnselectNetworkImage(val selectNetworkImage: String) : DataEvent()
    class OnAmount(val amount: String) : DataEvent()
    class OnPhoneNumber(val phoneNumber: String) : DataEvent()
    object OnContinue : DataEvent()
    class OnshowAndHidePinDialog(val showAndHidePinDialog: Boolean) : DataEvent()
    class OnPin(val pin: String) : DataEvent()
    class MessageDialog(val message: String = "", val viewStatus: Boolean = false): DataEvent()


    //this is the additions
    class OnDataPlanExpandNetworkWidget(val dataPlanExpandNetworkWidget: Boolean) : DataEvent()
    class OnDataPlanSelect(val dataPlanSelect: String, val id: Int) : DataEvent()
}

