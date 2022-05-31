package com.ilhomsoliev.volgaitamericanstocks.presentation.stocks_list_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ErrorLabel(
    error:String) {
    AnimatedVisibility(visible = error != "",
        enter = expandVertically(expandFrom = Alignment.Top),
        exit = shrinkVertically(shrinkTowards = Alignment.Bottom)
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)){
            Text(text = error,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.Center))
        }
    }
}