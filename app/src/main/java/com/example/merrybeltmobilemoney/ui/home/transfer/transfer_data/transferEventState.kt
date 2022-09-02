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
    val amount: String = ""
)

sealed class TransferEvent {
    class OnBankExpanded(val bankExpanded: Boolean) : TransferEvent()
    class OnBankLoader(val bankLoader: Boolean) : TransferEvent()
    class OnBankSelected(val bankSelected: String, val bankImage: String, val backCode: String) : TransferEvent()
    class OnAccountNumber(val accountNumber: String) : TransferEvent()
    class OnAccountName(val accountName: String) : TransferEvent()
    class OnAmount(val amount: String) : TransferEvent()
}
