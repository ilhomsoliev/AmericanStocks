package com.ilhomsoliev.volgaitamericanstocks.presentation.stocks_list_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilhomsoliev.volgaitamericanstocks.core.Resource
import com.ilhomsoliev.volgaitamericanstocks.domain.Repository
import com.ilhomsoliev.volgaitamericanstocks.domain.models.Stock
import com.ilhomsoliev.volgaitamericanstocks.domain.use_cases.GetStockSymbolsUseCase
import com.ilhomsoliev.volgaitamericanstocks.presentation.stocks_list_screen.states.StockSymbolsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StocksListViewModel @Inject constructor(
    private val apiRep: Repository,
    private val getStockSymbolsUseCase: GetStockSymbolsUseCase,

    ) : ViewModel() {

    private val _stateStockSymbols = mutableStateOf(StockSymbolsState())
    val stateStockSymbols: State<StockSymbolsState> = _stateStockSymbols

    init {
        getStockSymbols()
    }

    fun getStockSymbols() {
        getStockSymbolsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    val stockSymbols: List<Stock> =
                        result.data!!.map { Stock(it.symbol) }.sortedBy { it.symbol }
                    _stateStockSymbols.value = StockSymbolsState(response = stockSymbols)
                }
                is Resource.Loading -> {
                    _stateStockSymbols.value = StockSymbolsState(isLoading = true)
                }
                is Resource.Error -> {
                    _stateStockSymbols.value = StockSymbolsState(error = result.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun getStockPrice(symbol: String): Double {
        val response = apiRep.getStockPrice(symbol)
        _stateStockSymbols.value.response?.find { simpleStock -> simpleStock.symbol == symbol }?.price =
            response
        return response
    }
}
