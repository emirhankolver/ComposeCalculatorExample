package com.app.calculatordemo.ui.operations

sealed class CalculatorOperations(val symbol: String) {
    object Add : CalculatorOperations("+")
    object Subtract : CalculatorOperations("-")
    object Divide : CalculatorOperations("/")
    object Multiply : CalculatorOperations("x")

}