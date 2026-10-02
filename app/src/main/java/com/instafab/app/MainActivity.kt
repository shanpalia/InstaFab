package com.instafab.app

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showSplash()
    }

    private fun showSplash() {
        val root = layoutInflater.inflate(R.layout.activity_splash, null)
        setContentView(root)
        Handler(Looper.getMainLooper()).postDelayed({ showHome() }, 1800)
    }

    private fun showHome() {
        setContentView(R.layout.activity_main)
    }
}
