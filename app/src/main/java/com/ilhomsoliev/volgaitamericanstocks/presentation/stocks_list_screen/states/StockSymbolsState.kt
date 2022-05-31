package com.ilhomsoliev.volgaitamericanstocks.presentation.stocks_list_screen.states

import com.ilhomsoliev.volgaitamericanstocks.domain.models.Stock

data class StockSymbolsState(
    val isLoading: Boolean = false,
    var response: List<Stock>? = null,
    val error: String = ""
)