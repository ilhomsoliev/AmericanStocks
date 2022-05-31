package com.ilhomsoliev.volgaitamericanstocks.domain.use_cases

import com.ilhomsoliev.volgaitamericanstocks.core.Resource
import com.ilhomsoliev.volgaitamericanstocks.data.dto.StockSymbol
import com.ilhomsoliev.volgaitamericanstocks.domain.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetStockSymbolsUseCase @Inject constructor(
    private val api: Repository,
) {
    operator fun invoke(): Flow<Resource<List<StockSymbol>>> = flow{
        try {
            emit(Resource.Loading<List<StockSymbol>>())
            val serverStockSymbols: List<StockSymbol> = api.getStockList("US")

            emit(Resource.Success<List<StockSymbol>>(serverStockSymbols))
        } catch (exc: Exception) {
            emit(Resource.Error<List<StockSymbol>>(message = exc.message.toString()))
        }
    }
}
