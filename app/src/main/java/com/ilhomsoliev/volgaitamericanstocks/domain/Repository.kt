package com.ilhomsoliev.volgaitamericanstocks.domain

import com.ilhomsoliev.volgaitamericanstocks.data.dto.StockSymbol


interface Repository {

    suspend fun getStockList(
        exchange: String,
        mic: String = "",
        securityType: String = "",
        currency: String = "",
    ): List<StockSymbol>

    suspend fun getStockPrice(symbol: String): Double

}