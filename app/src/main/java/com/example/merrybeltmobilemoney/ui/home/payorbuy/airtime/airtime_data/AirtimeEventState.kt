package com.example.merrybeltmobilemoney.ui.home.payorbuy.airtime.airtime_data



data class AirtimeState(
    val difAirTimeNetwork: List<VariousAirtime> = emptyList(),
    val showAndHideLoader: Boolean = false,
    val selectedNetwork: String = "",
    val expandNetworkWidget: Boolean = false,
    val selectNetworkImage: String = "",
    val amount: String = "",
    val phoneNumber:String = "",
    val showAndHidePinDialog: Boolean = false,
    val pin: String = "",
    val messageDialogTitle: String = "Notification",
    val messageDialogContent: String = "",
    val messageDialogShowAndHide: Boolean = false,
)

sealed class AirtimeEvent {
    class OnExpandNetworkWidget(val expanded: Boolean) : AirtimeEvent()
    class OnselectedNetwork(val selectedNetwork: String) : AirtimeEvent()
    class OnselectNetworkImage(val selectNetworkImage: String) : AirtimeEvent()
    class OnAmount(val amount: String) : AirtimeEvent()
    class OnPhoneNumber(val phoneNumber: String) : AirtimeEvent()
    object OnContinue : AirtimeEvent()
    class OnshowAndHidePinDialog(val showAndHidePinDialog: Boolean) : AirtimeEvent()
    class OnPin(val pin: String) : AirtimeEvent()
    class MessageDialog(val message: String = "", val viewStatus: Boolean = false): AirtimeEvent()
}
