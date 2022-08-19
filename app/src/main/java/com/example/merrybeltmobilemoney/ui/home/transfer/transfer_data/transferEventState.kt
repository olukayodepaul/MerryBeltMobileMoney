package com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data


data class TransferState (
    //    var selectedItemIndex: Int = -1,
    val balances:String = "",
    val listOfBanks: List<AllBanks> = emptyList(),
    val expanded: Boolean = false,
    val specimen: String = "",
    val selectedBankLogo: String = "",
    val accNoToTransferTo: String = "",
    val accNameToTransferTo: String = "",
    val setBankCode: String = "",
)


sealed class TransferEvent {
//    class OnSelectedItemIndex(val selectedItemIndex: Int) : TransferEvent()
    class OnExpanded(val expanded: Boolean) : TransferEvent()
    class OnSpecimenText(val specimen:  String) : TransferEvent()
    class OnSelectedBankLogo(val bankLogo: String) : TransferEvent()
    class OnAccNoToTransferTo(val accNoToTransferTo: String) : TransferEvent()
    class OnSetBankCode(val setBankCode: String) : TransferEvent()
}




