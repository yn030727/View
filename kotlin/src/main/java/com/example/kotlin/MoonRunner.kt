package com.example.kotlin

import android.provider.Settings.Global

class MoonRunner : Runnable {
    var long : Long = 0
    override fun run() {
        while (!Thread.currentThread().isInterrupted){
            long++
            println("long = $long")
        }
        println("stop")


    }

}