package com.gfg.pie_chart_jetpack

import android.graphics.Typeface
import android.os.Bundle

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.gfg.pie_chart_jetpack.ui.theme.*

import com.gfg.pie_chart_jetpack.ui.theme.Pie_Chart_JetpackTheme
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import java.util.*

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pie_Chart_JetpackTheme {
                // on below line we are specifying
                // background color for our application
                Surface(color = MaterialTheme.colorScheme.primary) {
                    // on below line we are
                    // specifying theme as scaffold
                    Scaffold(
                        // on below line we are specifying
                        // top bar as our action bar.
                        topBar = {
                            TopAppBar(
                                // on below line we are specifying title
                                // for our action bar as Speech to Text
                                title = {
                                    Text(
                                        // on below line we are specifying text
                                        // for our text view.
                                        text = "Pie Chart Example",

                                        // on below line we are specifying
                                        // width of our text
                                        modifier = Modifier.fillMaxWidth(),

                                        // on below line we are specifying the
                                        // text alignment for our text
                                        textAlign = TextAlign.Center
                                    )
                                })
                        },
                        content = { paddingValues -> // Take paddingValues as a parameter
                        Box(modifier = Modifier.padding(paddingValues)) { // Apply padding
                            PieChart()
                        }
                    })
                }
            }
        }
    }

    // on below line we are creating a
    // pie chart function on below line.
    @Composable
    fun PieChart() {
        // on below line we are creating a column
        // and specifying a modifier as max size.
        Column(modifier = Modifier.fillMaxSize()) {
            // on below line we are again creating a column
            // with modifier and horizontal and vertical arrangement
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // on below line we are creating a simple text
                // and specifying a text as Web browser usage share
                Text(
                    text = "Web Browser Usage Share",

                    // on below line we are specifying style for our text
                    style = TextStyle.Default,

                    // on below line we are specifying font family.
                    fontFamily = FontFamily.Default,

                    // on below line we are specifying font style
                    fontStyle = FontStyle.Normal,

                    // on below line we are specifying font size.
                    fontSize = 20.sp
                )

                // on below line we are creating a column and
                // specifying the horizontal and vertical arrangement
                // and specifying padding from all sides.
                Column(
                    modifier = Modifier
                        .padding(18.dp)
                        .size(320.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // on below line we are creating a cross fade and
                    // specifying target state as pie chart data the
                    // method we have created in Pie chart data class.
                    Crossfade(targetState = getPieChartData) { pieChartData ->
                        // on below line we are creating an
                        // android view for pie chart.
                        AndroidView(factory = { context ->
                            // on below line we are creating a pie chart
                            // and specifying layout params.
                            PieChart(context).apply {
                                layoutParams = LinearLayout.LayoutParams(
                                    // on below line we are specifying layout
                                    // params as MATCH PARENT for height and width.
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                )
                                // on below line we are setting description
                                // enables for our pie chart.
                                this.description.isEnabled = false

                                // on below line we are setting draw hole
                                // to false not to draw hole in pie chart
                                this.isDrawHoleEnabled = false

                                // on below line we are enabling legend.
                                this.legend.isEnabled = true

                                // on below line we are specifying
                                // text size for our legend.
                                this.legend.textSize = 14F

                                // on below line we are specifying
                                // alignment for our legend.
                                this.legend.horizontalAlignment =
                                    Legend.LegendHorizontalAlignment.CENTER

                                // on below line we are specifying entry label color as white.
                                ContextCompat.getColor(context, R.color.white)
                                //this.setEntryLabelColor(resources.getColor(R.color.white))
                            }
                        },
                            // on below line we are specifying modifier
                            // for it and specifying padding to it.
                            modifier = Modifier
                                .wrapContentSize()
                                .padding(5.dp), update = {
                                // on below line we are calling update pie chart
                                // method and passing pie chart and list of data.
                                updatePieChartWithData(it, pieChartData)
                            })
                    }
                }
            }
        }
    }

    // on below line we are creating a update pie
    // chart function to update data in pie chart.
    fun updatePieChartWithData(
        // on below line we are creating a variable
        // for pie chart and data for our list of data.
        chart: PieChart,
        data: List<PieChartData>
    ) {
        // on below line we are creating
        // array list for the entries.
        val entries = ArrayList<PieEntry>()

        // on below line we are running for loop for
        // passing data from list into entries list.
        for (i in data.indices) {
            val item = data[i]
            entries.add(PieEntry(item.value ?: 0.toFloat(), item.browserName ?: ""))
        }

        // on below line we are creating
        // a variable for pie data set.
        val ds = PieDataSet(entries, "")

        // on below line we are specifying color
        // int the array list from colors.
        ds.colors = arrayListOf(
            greenColor.toArgb(),
            blueColor.toArgb(),
            redColor.toArgb(),
            yellowColor.toArgb(),
        )
        // on below line we are specifying position for value
        ds.yValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE

        // on below line we are specifying position for value inside the slice.
        ds.xValuePosition = PieDataSet.ValuePosition.INSIDE_SLICE

        // on below line we are specifying
        // slice space between two slices.
        ds.sliceSpace = 2f

        // on below line we are specifying text color
        ContextCompat.getColor(this, R.color.white)
        // ds.valueTextColor = resources.getColor(R.color.white)

        // on below line we are specifying
        // text size for value.
        ds.valueTextSize = 18f

        // on below line we are specifying type face as bold.
        ds.valueTypeface = Typeface.DEFAULT_BOLD

        // on below line we are creating
        // a variable for pie data
        val d = PieData(ds)

        // on below line we are setting this
        // pie data in chart data.
        chart.data = d

        // on below line we are
        // calling invalidate in chart.
        chart.invalidate()
    }
}
