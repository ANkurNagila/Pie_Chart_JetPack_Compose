package com.gfg.pie_chart_jetpack

data class PieChartData(
    var browserName: String?,
    var value: Float?
)

// on below line we are creating a method
// in which we are passing all the data.
val getPieChartData = listOf(
    PieChartData("Chrome", 34.68F),
    PieChartData("Firefox", 16.60F),
    PieChartData("Safari", 16.15F),
    PieChartData("Internet Explorer", 15.62F),
)