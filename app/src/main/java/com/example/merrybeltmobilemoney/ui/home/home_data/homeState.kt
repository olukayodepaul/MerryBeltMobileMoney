package com.example.merrybeltmobilemoney.ui.home.home_data

import androidx.compose.ui.geometry.Size
import com.example.merrybeltmobilemoney.R

data class HomeState(
    val customerId: String = "",
    val balance: String = "",
    val banklist: List<Banks.BankList> = emptyList(),
    val bankLogo: String = "",
    //Expandable Menu
    var size: Size = Size.Zero,
    val enable: Boolean = false,
    var value: String = "",
    var selectedItemIndex: Int = -1,

    var icon: Int = if (enable) {
        R.drawable.ic_baseline_content_paste_search_24
    } else {
        R.drawable.ic_baseline_network_check_24
    },

    val accNumber: String = "",
    val accName: String = "",
    val accAmount: String = "",
    val accRemark: String = "",
    val enableWidget: Boolean = false
)