package com.app.calculatordemo.ui.actions

import com.app.calculatordemo.ui.operations.CalculatorOperations

sealed class CalculatorAction {
    data class Number(val number: Int) : CalculatorAction()
    object Clear : CalculatorAction()
    object Delete : CalculatorAction()
    object Decimal : CalculatorAction()
    object Calculate : CalculatorAction()
    data class Operation(val operations: CalculatorOperations) : CalculatorAction()
}
