package com.example.asoro.healthygroceryassistant.ui.intermittent_fasting

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.SharedPreferences
import android.os.CountDownTimer
import com.example.asoro.healthygroceryassistant.MyApp
import com.example.asoro.healthygroceryassistant.R
import com.example.asoro.healthygroceryassistant.db.FastPeriod
import com.example.asoro.healthygroceryassistant.db.MyDatabase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class IntermittentFastingViewModel:ViewModel(){

    var sharedPrefs:SharedPreferences?=null
    var timer:CountDownTimer?=null
    var fastStart:Date?=null
    @Inject
    lateinit var myDatabase: MyDatabase


    private val dateFormat = "yyyy-MM-dd HH:mm:ss"

    init {
        MyApp.sAppComponent.inject(this)
    }


    fun startFast(context:Context, onIntermittentFastingTimerEventListener: OnIntermittentFastingTimerEventListener){
        sharedPrefs = context.getSharedPreferences("Intermittent_Fasting_Info", Context.MODE_PRIVATE)
        var edit = (sharedPrefs)?.edit()
        fastStart = Calendar.getInstance().time
        val df = SimpleDateFormat(dateFormat)
        edit?.putString(context.resources.getString(R.string.last_fast_start), df.format(fastStart))

        timer= object:CountDownTimer(16*60*60*1000, 500){
            override fun onFinish() {
                onIntermittentFastingTimerEventListener.onFinish()
            }
            override fun onTick(millisUntilFinished: Long) {
                onIntermittentFastingTimerEventListener.onTick(millisUntilFinished)
            }
        }
        timer?.start()

    }


    fun stopFast(context: Context){
        timer?.cancel()
        sharedPrefs = context.getSharedPreferences("Intermittent_Fasting_Info", Context.MODE_PRIVATE)
        var edit = (sharedPrefs)?.edit()
        val df = SimpleDateFormat(dateFormat)
        val date = df.format(Calendar.getInstance().time)
        edit?.putString(context.resources.getString(R.string.last_fast_end), date)
        if(fastStart!=null){
            Single.fromCallable {
                myDatabase.intermittentFastingDAO().insert(FastPeriod(fastStart as Date, Calendar.getInstance().time))
            }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe()
        }
    }


    interface OnIntermittentFastingTimerEventListener {
        fun onTick(millisUntilFinished: Long)
        fun onFinish()
    }

}