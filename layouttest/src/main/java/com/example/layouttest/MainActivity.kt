package com.example.layouttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import okhttp3.FormBody
import okhttp3.RequestBody

class MainActivity : AppCompatActivity() {
    val API_KEY = "5GHSlGSkqlDiGA57zWmilkwp"
    val SECRET_KEY = "UAbadAXq7Evl1qbg9j9aWGcXibZ8oNH8"

    var formBody : RequestBody = FormBody.Builder()
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    
}