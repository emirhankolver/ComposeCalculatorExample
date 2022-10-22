package com.app.calculatordemo.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.app.calculatordemo.ui.actions.CalculatorAction
import com.app.calculatordemo.ui.operations.CalculatorOperations
import com.app.calculatordemo.ui.states.CalculatorState

class CalculatorViewModel : ViewModel() {

    var state by mutableStateOf(CalculatorState())
        private set


    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Operation -> enterOperation(action.operations)
            CalculatorAction.Decimal -> enterDecimal()
            CalculatorAction.Clear -> state = CalculatorState()
            CalculatorAction.Calculate -> performCalculation()
            CalculatorAction.Delete -> performDeletion()
        }
    }

    private fun performDeletion() {
        when {
            state.number2.isNotEmpty() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )
            state.operation == null -> state.copy(
                operation = null
            )
            state.number1.isNotEmpty() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
    }

    private fun performCalculation() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if (number1 != null && number2 != null) {
            val result = when (state.operation) {
                CalculatorOperations.Add -> number1 + number2
                CalculatorOperations.Divide -> number1 / number2
                CalculatorOperations.Multiply -> number1 * number2
                CalculatorOperations.Subtract -> number1 - number2
                null -> return
            }
            state = state.copy(
                number1 = result.toString().take(15),
                number2 = "",
                operation = null,
            )
        }
    }

    private fun enterOperation(operations: CalculatorOperations) {
        if (state.number1.isNotEmpty()) {
            state = state.copy(operation = operations)
        }
    }

    private fun enterDecimal() {
        if (state.operation == null && !state.number1.contains(".") && state.number1.isNotEmpty()) {
            state = state.copy(
                number1 = state.number1 + "."
            )
            return
        }
        if (!state.number2.contains(".") && state.number2.isNotEmpty()) {
            state = state.copy(
                number2 = state.number2 + "."
            )
            return
        }

    }

    private fun enterNumber(number: Int) {
        if (state.operation == null) {
            // we're at first number.
            if (state.number1.length >= MAX_NUMBER_LENGTH) return
            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }
        // We're at second number.
        if (state.number2.length >= MAX_NUMBER_LENGTH) return
        state = state.copy(
            number2 = state.number2 + number
        )
    }

    companion object {
        private const val MAX_NUMBER_LENGTH = 8
    }
}