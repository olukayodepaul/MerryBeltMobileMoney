package com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data


data class TransferState (
    val balances:String = "",
    val listOfBanks: List<AllBanks> = emptyList(),
    val expanded: Boolean = false,
    val specimen: String = "",
    val selectedBankLogo: String = "",
    val accNoToTransferTo: String = "",
    val accNameToTransferTo: String = "",
    val setBankCode: String = "",
    val setBankName: String = "",
    val loader: Boolean = false,
    val amountToTransfer: String = "",
    val continueButtonEnable: Boolean = false, //this is the continue button
    val showAndHidePinDialog: Boolean = false,
    val enteringPin: String = ""

)


sealed class TransferEvent {
    class OnExpanded(val expanded: Boolean) : TransferEvent()
    class OnSpecimenText(val specimen:  String) : TransferEvent()
    class OnSelectedBankLogo(val bankLogo: String) : TransferEvent()
    class OnAccNoToTransferTo(val accNoToTransferTo: String) : TransferEvent()
    class OnSetBankCode(val setBankCode: String) : TransferEvent()
    class OnSetBankName(val setBankName: String) : TransferEvent()
    class OnAmountToTransfer(val amountToTransfer: String) : TransferEvent()
    object OnClickContButton : TransferEvent()
    class OnShowAndHidePinDialog(val showAndHidePinDialog: Boolean) : TransferEvent()
    object OnClickOnDoneButton: TransferEvent()
    class OnEnteringPin(val enteringPin: String) : TransferEvent()
}




