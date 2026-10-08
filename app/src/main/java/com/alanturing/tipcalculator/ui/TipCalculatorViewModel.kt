package com.alanturing.tipcalculator.ui


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TipCalculatorViewModel: ViewModel() {

    private val _state = MutableStateFlow(TipCaculatorScreenState())
    val state: StateFlow<TipCaculatorScreenState>
        get() = _state.asStateFlow()

    fun changeGuests(text: String){
        val oldState = state.value
        _state.value = oldState.copy(
            guests = text,
            isCaculateEnabled = isCalculatedEnable(oldState.amount, text)
        )
    }

    fun changeAmount(text: String){
        val oldState = state.value
        _state.value = oldState.copy(
            amount = text,
            isCaculateEnabled = isCalculatedEnable(text, oldState.guests)
        )
    }

    fun changeTipValue(float: Float){
        val oldState = state.value
        _state.value = oldState.copy(
            tipAmount = float
        )
    }

    fun calculateSplit() {
        val oldState = state.value
        val guestsNumber = oldState.guests.toIntOrNull()!!
        val totalAmount = oldState.amount.toDoubleOrNull()!!
        val cantPropina = if (oldState.tip){
            when(oldState.tipAmount) {
                0.00F -> 1.0f
                1.00F -> 1.05f
                2.00F -> 1.1f
                3.00F -> 1.15f
                4.00F -> 1.2f
                else -> error("No puede ser posible esto xd")
            }
        } else 1.0f

        _state.value = oldState.copy(
            result = ((totalAmount * cantPropina.toDouble()) / guestsNumber).toString()
        )
    }

    fun changeTip(isEnabled:Boolean) {
        val oldState = state.value
        _state.value = oldState.copy(
            tip = isEnabled
        )
    }



    fun isCalculatedEnable(amount: String, guests: String): Boolean{
        val guestsNumber = guests.toIntOrNull()
        val totalAmount = amount.toDoubleOrNull()

        return if(guestsNumber != null && totalAmount != null)
            guestsNumber > 0 && totalAmount > 0
        else
            false
    }


}

data class TipCaculatorScreenState(
    val guests:String = "0",
    val amount:String = "0.00",
    val tip:Boolean = false,
    val tipAmount:Float = 0.0F,
    val result:String = "",
    val isCaculateEnabled:Boolean = false

)