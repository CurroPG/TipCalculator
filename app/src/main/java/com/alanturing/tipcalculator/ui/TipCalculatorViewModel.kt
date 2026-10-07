package com.alanturing.tipcalculator.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TipCalculatorViewModel(): ViewModel() {

    private val _state = MutableStateFlow(TipCaculatorScreenState())
    val state: StateFlow<TipCaculatorScreenState>
        get() = _state.asStateFlow()

    fun calculateSplit() {

    }

    fun changeTip(isEnabled:Boolean) {
        val oldState = state.value
        _state.value = oldState.copy(
            tip = isEnabled
        )
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