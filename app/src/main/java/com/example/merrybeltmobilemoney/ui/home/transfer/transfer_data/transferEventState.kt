package com.example.merrybeltmobilemoney.ui.home.transfer.transfer_data


data class TransferState (
    val balances:String = "",
//    val accountNumber:String = "",
//    val inputtedAccNo:String = "",
//    val inputtedAccName:String = "",
//    val enableWidget: Boolean = false,
//    val inputtedAmount: String = "",
//    val inputtedRemark: String = "",

//    var value: String = "",
//    val enable: Boolean = false,

//    var selectedItemIndex: Int = -1,


    val listOfBanks: List<AllBanks> = emptyList(),
    val expanded: Boolean = false,
    val specimen: String = "",
    val selectedBankLogo: String = "",

)


sealed class TransferEvent {
//    class OnChangeAccNumber(val accountNumber:  String): TransferEvent()
//    class OnChangeBalance(val balance: String): TransferEvent()
//    class OnChangeInputtedAccNo(val accNo: String): TransferEvent()
//    class OnChangeInputtedAccName(val accName: String): TransferEvent()
//    class OnChangeInputtedAmount(val amount: String): TransferEvent()
//    class OnChangeInputtedRemark(val remark: String): TransferEvent()
//    class OnEnable(val enable: Boolean) : TransferEvent()
//    class OnValue(val value: String) : TransferEvent()
//    class OnSelectedItemIndex(val selectedItemIndex: Int) : TransferEvent()

    class OnExpanded(val expanded: Boolean) : TransferEvent()
    class OnSpecimenText(val specimen:  String) : TransferEvent()
    class OnSelectedBankLogo(val bankLogo: String) : TransferEvent()

}




