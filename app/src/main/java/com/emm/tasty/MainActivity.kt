package com.emm.tasty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.emm.data.testfunctions.RandomClass

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RandomClass.log()
    }
}