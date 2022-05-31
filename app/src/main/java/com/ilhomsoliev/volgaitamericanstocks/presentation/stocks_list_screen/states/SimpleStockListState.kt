package com.ilhomsoliev.volgaitamericanstocks.presentation.stocks_list_screen.states

import com.ilhomsoliev.volgaitamericanstocks.domain.models.Stock

class SimpleStockListState (
    val isLoading: Boolean = false,
    var response: List<Stock>?= null,
    val error: String = ""
)