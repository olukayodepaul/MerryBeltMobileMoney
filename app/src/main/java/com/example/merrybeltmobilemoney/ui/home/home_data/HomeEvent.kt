package com.example.merrybeltmobilemoney.ui.home.home_data

import androidx.compose.ui.geometry.Size

sealed class HomeEvent {
    class onEnable(val enable: Boolean) : HomeEvent()
    class onValue(val value: String) : HomeEvent()
    class onSelectedItemIndex(val selectedItemIndex: Int) : HomeEvent()
    //class onIcon(val icon: Int): HomeEvent()
    class onSize(val size: Size) : HomeEvent()
    class onBankLog(val bankLogo: String) : HomeEvent()
    class onAccountNumber(val accNumber: String) : HomeEvent()
    class onAccountName(val accName: String) : HomeEvent()
    class onAmount(val accAmount: String) : HomeEvent()
    class onRemark(val accRemark: String) : HomeEvent()
}