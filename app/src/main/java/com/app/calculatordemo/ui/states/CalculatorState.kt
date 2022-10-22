package com.app.calculatordemo.ui.states

import com.app.calculatordemo.ui.operations.CalculatorOperations

data class CalculatorState(
    val number1: String = "",
    val number2: String = "",
    val operation: CalculatorOperations? = null
)
