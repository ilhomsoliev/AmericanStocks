package com.ilhomsoliev.volgaitamericanstocks.presentation.stocks_list_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.volgaitamericanstocks.domain.models.Stock
import com.ilhomsoliev.volgaitamericanstocks.presentation.theme.secondMainColor
import kotlinx.coroutines.delay


@Composable
fun StockListItem(
    stock: Stock,
    viewModel: StocksListViewModel,
) {

    var stockPrice by remember {
        mutableStateOf(stock.price)
    }

    LaunchedEffect(key1 = stock.symbol) {
        stockPrice = stock.price
        while (stockPrice == 0.00) {
            val sPrice = viewModel.getStockPrice(symbol = stock.symbol)
            stockPrice = sPrice
            delay(6000)
        }
    }

    Box(
        Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(4.dp)
            .clip(RoundedCornerShape(8))
            .border(1.dp, secondMainColor, RoundedCornerShape(8))
    ) {
        Text(
            text = stock.symbol,
            modifier = Modifier
                .padding(start = 24.dp)
                .align(Alignment.CenterStart),
            fontSize = 24.sp
        )
        if (stockPrice == 0.00)
            CircularProgressIndicator(
                color = secondMainColor,
                modifier = Modifier
                    .padding(end = 24.dp)
                    .align(Alignment.CenterEnd),
            )
        else
            Text(
                text = "$stockPrice $",
                modifier = Modifier
                    .padding(end = 24.dp)
                    .align(Alignment.CenterEnd),
                fontSize = 24.sp,
                color = Color(0xFF85bb65)
            )
    }
}