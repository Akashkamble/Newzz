package com.akash.newsapp.ui

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.akash.newsapp.NewsViewModel
import com.akash.newsapp.internals.Constants
import com.akash.newsapp.R
import com.akash.newsapp.data.converters.DateConverters
import org.koin.android.viewmodel.ext.android.viewModel
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

class NewsActivity : AppCompatActivity() {
    val TAG = NewsActivity::class.java.simpleName

    val newsViewModel : NewsViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        var string = "2019-01-26T23:05:00Z"
//        var offsetDateTime : OffsetDateTime = DateConverters.toOffsetDateTime(string)!!
//        var abc = DateConverters.fromOffsetDateTime(offsetDateTime)
//        var time : ZonedDateTime = DateConverters.getDate(string).withZoneSameLocal(ZoneId.systemDefault())
//        Log.e(TAG,"Hour: ${time.toLocalDateTime().hour} ${time.toLocalDateTime().minute} : ${time.toLocalDateTime().dayOfMonth} : $abc")
        newsViewModel.getHeadLines()
        newsViewModel.errorMessage.observe(this, Observer {
            if(!it.isNullOrEmpty())
            Toast.makeText(baseContext,it,Toast.LENGTH_SHORT).show()
        })
        newsViewModel.headLineList.observe(this, Observer {
            if(it?.size!! >0){
                Log.e(TAG, it[0].description)
            }
        })
    }
}
