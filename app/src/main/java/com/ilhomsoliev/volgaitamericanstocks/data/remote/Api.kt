package com.ilhomsoliev.volgaitamericanstocks.data.remote

import com.ilhomsoliev.volgaitamericanstocks.data.dto.StockPrice
import com.ilhomsoliev.volgaitamericanstocks.data.dto.StockSymbol
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("stock/symbol")
    suspend fun getStocksList(
        @Query("exchange") exchange:String,
        @Query("mic") mic:String = "",
        @Query("securityType") securityType:String = "",
        @Query("currency") currency:String = "",
    ):List<StockSymbol>

    @GET("quote")
    suspend fun getStockPrice(
        @Query("symbol") symbol:String
    ):Response<StockPrice>


}