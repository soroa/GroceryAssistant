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
        var barChart = findViewById(R.id.chart) as BarChart

        val dataSet: BarDataSet = BarDataSet(createFastData(), "fast")
        dataSet.setColor(getResources().getColor(R.color.darkRed))

        val barData = BarData(dataSet)
        barChart.getXAxis().setValueFormatter(IndexAxisValueFormatter(createLabelsData()));
        barChart.setFitBars(true)
        barChart.data = barData
        barChart.invalidate()
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
        list.add("Monday")
        list.add("Tuesday")
        list.add("Wednesday")
        list.add("Thursday")
        list.add("Friday")

        return list
    }
}
