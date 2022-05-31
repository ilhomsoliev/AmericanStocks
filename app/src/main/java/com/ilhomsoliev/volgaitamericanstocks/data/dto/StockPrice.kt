package com.ilhomsoliev.volgaitamericanstocks.data.dto


import com.google.gson.annotations.SerializedName

data class StockPrice(
    @SerializedName("c")
    val c: Double,
    @SerializedName("h")
    val h: Double,
    @SerializedName("l")
    val l: Double,
    @SerializedName("o")
    val o: Double,
    @SerializedName("pc")
    val pc: Double,
    @SerializedName("t")
    val t: Int
)