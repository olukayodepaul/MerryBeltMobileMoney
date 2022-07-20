package com.example.merrybeltmobilemoney.ui.home


import android.widget.Toast
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merrybeltmobilemoney.Application
import com.example.merrybeltmobilemoney.provider.api.api_provider_domain.MerryBeltApiRepository
import com.example.merrybeltmobilemoney.ui.home.home_data.Banks
import com.example.merrybeltmobilemoney.ui.home.home_data.HomeEvent
import com.example.merrybeltmobilemoney.ui.home.home_data.HomeState
import com.example.merrybeltmobilemoney.ui.home.home_data.TestData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: MerryBeltApiRepository, private val appContext: Application
):ViewModel() {

    //state
    var uiState = MutableStateFlow(HomeState())

    private fun setCustomerId(customerId: String, balance: String) {
        uiState.value = uiState.value.copy(
            customerId = customerId,
            balance = balance
        )
    }

    private fun setBankList(bankList: List<Banks.AllBanks>) {
        uiState.value = uiState.value.copy(
            banklist = bankList,
        )
    }

    //Setting all the expandable values
    private fun onEnable(enable: Boolean) {
        uiState.value = uiState.value.copy(enable = enable)
    }

    private fun onValue(value: String) {

        if (value.isNotEmpty() && uiState.value.accNumber.length == 10) {
            uiState.value = uiState.value.copy(enableWidget = true)
        } else {
            uiState.value = uiState.value.copy(enableWidget = false)
        }
        uiState.value = uiState.value.copy(value = value)
    }

    private fun onSelectedItemIndex(selectedItemIndex: Int) {
        uiState.value = uiState.value.copy(selectedItemIndex = selectedItemIndex,)
    }

    private fun onSize(size: Size) {
        uiState.value = uiState.value.copy(size = size)
    }

    private fun onBankLogo(bankLogo: String) {
        uiState.value = uiState.value.copy(bankLogo = bankLogo)
    }

    private fun onAccountNumber(accNumber: String) {

        if (uiState.value.value.isNotEmpty() && accNumber.length == 10) {
            uiState.value = uiState.value.copy(
                enableWidget = true
            )
        } else {
            uiState.value = uiState.value.copy(
                enableWidget = false
            )
        }

        uiState.value = uiState.value.copy(
            accNumber = accNumber
        )
    }

    private fun onAccountName(accName: String) {
        uiState.value = uiState.value.copy(
            accName = accName
        )
    }

    private fun onAccAmount(accAmount: String) {
        uiState.value = uiState.value.copy(
            accAmount = accAmount
        )
    }

    private fun onAccRemark(accRemark: String) {
        uiState.value = uiState.value.copy(
            accRemark = accRemark
        )
    }

    //event
    fun homeEventHandler(event: HomeEvent) {
        when (event) {
            is HomeEvent.onEnable -> {
                onEnable(event.enable)
            }
            is HomeEvent.onValue -> {
                onValue(event.value)
            }
            is HomeEvent.onSelectedItemIndex -> {
                onSelectedItemIndex(event.selectedItemIndex)
            }
            is HomeEvent.onSize -> {
                onSize(event.size)
            }

            is HomeEvent.onBankLog -> {
                onBankLogo(event.bankLogo)
            }

            is HomeEvent.onAccountNumber -> {
                onAccountNumber(event.accNumber)
            }

            is HomeEvent.onAccountName -> {
                onAccountName(event.accName)
            }

            is HomeEvent.onAmount -> {
                onAccAmount(event.accAmount)
            }

            is HomeEvent.onRemark -> {
                onAccRemark(event.accRemark)
            }
        }
    }

    init {
        viewModelScope.launch {

            try {

                var t = TestData(
                    amount =  30.0
                )
                val isBankList = repo.getBanks(terminalId = repo.loadUserInfo().terminalId, sessionId = repo.loadUserInfo().sessionId, t)
                val responseFromBankList = isBankList.body()!!.data
                Toast.makeText(appContext, "1 ${responseFromBankList}", Toast.LENGTH_LONG).show()

            } catch (e: Throwable) {
                Toast.makeText(appContext, "2 ${e.message}", Toast.LENGTH_LONG).show()
            }

            setCustomerId(
                customerId =  repo.loadUserInfo().accountNumber,
                balance = NumberFormat.getNumberInstance(Locale.US)
                    .format(repo.loadUserInfo().balance!!.toInt())
                    .replace(oldValue = ",", newValue = "،"),
            )
        }
    }

}