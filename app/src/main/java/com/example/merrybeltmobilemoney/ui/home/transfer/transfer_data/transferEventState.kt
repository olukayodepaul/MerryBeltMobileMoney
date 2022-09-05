package com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data

data class TransferState(
    val bankList: List<AllBanks> = emptyList(),
    val bankLoader: Boolean = false,
    val bankSelected: String = "",
    val bankExpanded: Boolean = false,
    val bankImage: String = "",
    val backCode: String = "",
    val accountNumber: String = "",
    val accountName: String = "",
    val amount: String = "",
    val msgTitle: String = "",
    val msg: String = "",
    val msgVisibility: Boolean = false,
    val hideAnsShowPinDialog:Boolean = false,
    val bankPin:String = "",
    val successHideAndShowDialog:  Boolean  = false
)

sealed class TransferEvent {
    class OnBankExpanded(val bankExpanded: Boolean) : TransferEvent()
    class OnBankLoader(val bankLoader: Boolean) : TransferEvent()
    class OnBankSelected(val bankSelected: String, val bankImage: String, val backCode: String) : TransferEvent()
    class OnAccountNumber(val accountNumber: String) : TransferEvent()
    class OnAccountName(val accountName: String) : TransferEvent()
    class OnAmount(val amount: String) : TransferEvent()
    class OnMessageDialog(val msgTitle: String, val msgVisibility: Boolean, val msg: String): TransferEvent()
    object OnContinue : TransferEvent()
    class OnHideAnsShowPinDialog(val hideAnsShowPinDialog: Boolean): TransferEvent()
    class OnBankPin(val bankPin: String): TransferEvent()
    object OnTransfer: TransferEvent()
}
