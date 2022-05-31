package com.ilhomsoliev.volgaitamericanstocks.presentation.stocks_list_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ilhomsoliev.volgaitamericanstocks.domain.models.Stock
import com.ilhomsoliev.volgaitamericanstocks.presentation.theme.mainColor

@Composable
fun StocksListScreen(
    viewModel: StocksListViewModel = hiltViewModel()
) {

    val stateStockSymbols by viewModel.stateStockSymbols

    val refreshState = rememberSwipeRefreshState(isRefreshing = stateStockSymbols.isLoading)

    Scaffold(topBar = {
        Column() {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                backgroundColor = mainColor,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "American Stocks",
                        color = Color.White,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                    IconButton(onClick = {
                        viewModel.getStockSymbols()
                    }) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
                    }

                }
            }
            ErrorLabel(error = stateStockSymbols.error)
        }
    }) {
        Box(
            Modifier
                .fillMaxSize()
        ) {
            SwipeRefresh(
                state = refreshState,
                onRefresh = { viewModel.getStockSymbols() },
                indicator = { state, refreshDist ->
                    SwipeRefreshIndicator(
                        state = state,
                        refreshTriggerDistance = refreshDist,
                        contentColor = mainColor
                    )
                }
            ) {
                LazyColumn(
                    state = rememberLazyListState(), modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (stateStockSymbols.isLoading)
                        item {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .height(700.dp)
                            ) {
                                CircularProgressIndicator(
                                    color = mainColor,
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    else
                        stateStockSymbols.response?.let {
                            itemsIndexed(it) { index: Int, simpleStock: Stock ->
                                StockListItem(
                                    stock = simpleStock,
                                    viewModel = viewModel,
                                )
                            }
                        }

                }
            }
        }
    }
}