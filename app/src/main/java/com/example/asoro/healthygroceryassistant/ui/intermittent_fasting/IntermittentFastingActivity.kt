package com.example.asoro.healthygroceryassistant.ui.intermittent_fasting

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.asoro.healthygroceryassistant.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.android.synthetic.main.activity_intermittent_fasting.*
import java.util.*
import java.util.concurrent.TimeUnit

class IntermittentFastingActivity : AppCompatActivity(), IntermittentFastingViewModel.OnIntermittentFastingTimerEventListener {
    private var viewModel: IntermittentFastingViewModel? = null
    private var isFasting: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intermittent_fasting)
        viewModel = ViewModelProviders.of(this).get(IntermittentFastingViewModel::class.java)
        setUpChart()
        start_stop_fast.setOnClickListener({ v ->

            if (!isFasting) {

                viewModel?.startFast(this, this)
                start_stop_fast.text = getString(R.string.stop_fasting)
            } else {
                viewModel?.stopFast(this)
                start_stop_fast.text = getString(R.string.start_fasting)
            }
            isFasting = !isFasting
        })
    }

    override fun onTick(millisUntilFinished: Long) {
        val hour = TimeUnit.MILLISECONDS.toHours(millisUntilFinished)
        val min = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60
        val sec = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60
        timer_tv.setText("$hour:$min:$sec")
    }

    override fun onFinish() {
        timer_tv.setText("Done")
    }

    fun setUpChart(){
        var barChart = findViewById(R.id.chart) as BarChart

        val dataSet: BarDataSet = BarDataSet(createFastData(), "fast")
        dataSet.setColors(getResources().getColor(R.color.darkRed), getResources().getColor(R.color.darkGreen))
        val barData = BarData(dataSet)
        barChart.getXAxis().setValueFormatter(IndexAxisValueFormatter(createLabelsData()));
        barChart.setFitBars(true)
        barChart.xAxis.setDrawGridLines(false)
        barChart.axisRight.setDrawAxisLine(false)
        barChart.axisRight.setDrawGridLines(false)
        barChart.axisRight.setDrawLabels(false)
        barChart.xAxis.setDrawAxisLine(false)
        barChart.axisLeft.setDrawAxisLine(false)
        barChart.axisLeft.setDrawGridLines(false)
        barChart.data = barData
        barChart.animateXY(2000,2000)
        barChart.axisLeft.axisMinimum=0f
        barChart.axisLeft.axisMaximum=24f
        barChart.invalidate()
    }

    fun createFastData(): List<BarEntry> {
        var list = ArrayList<BarEntry>()
        list.add(BarEntry(0F, 6.5F))
        list.add(BarEntry(1F, 7.6F))
        list.add(BarEntry(2F, 6.64F))
        list.add(BarEntry(3F, 14F))
        list.add(BarEntry(4F, 12F))
        return list
    }

    fun createLabelsData(): List<String> {
        var list = ArrayList<String>()
        list.add("Mon")
        list.add("Tue")
        list.add("Wed")
        list.add("Th")
        list.add("Fri")

        return list
    }
}
