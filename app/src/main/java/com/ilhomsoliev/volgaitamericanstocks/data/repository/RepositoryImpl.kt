package com.ilhomsoliev.volgaitamericanstocks.data.repository

import com.ilhomsoliev.volgaitamericanstocks.data.remote.Api
import com.ilhomsoliev.volgaitamericanstocks.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject
constructor(
    private val api: Api
) : Repository {

    override suspend fun getStockList(
        exchange: String,
        mic: String,
        securityType: String,
        currency: String
    ) = api.getStocksList(exchange, mic, securityType, currency)


    override suspend fun getStockPrice(symbol: String): Double {
        return try {
            val connect = api.getStockPrice(symbol = symbol)
            if (connect.isSuccessful) {
                connect.body()?.c ?: 0.0
            }
            else {
                0.0
            }
        } catch (e: Exception) {
            0.0
        }
    }
}