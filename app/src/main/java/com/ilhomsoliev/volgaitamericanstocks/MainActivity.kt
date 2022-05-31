package com.ilhomsoliev.volgaitamericanstocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ilhomsoliev.volgaitamericanstocks.presentation.stocks_list_screen.StocksListScreen
import com.ilhomsoliev.volgaitamericanstocks.presentation.theme.VolgaITAmericanStocksTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VolgaITAmericanStocksTheme {
                StocksListScreen()
            }
        }
    }
}