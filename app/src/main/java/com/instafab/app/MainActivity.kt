package com.instafab.app

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showSplash()
    }

    private fun showSplash() {
        val root = android.widget.LinearLayout(this).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            gravity = android.view.Gravity.CENTER
            setBackgroundColor(android.graphics.Color.WHITE)
        }
        val icon = android.widget.ImageView(this).apply {
            setImageResource(com.instafab.app.R.drawable.ic_instafab)
        }
        root.addView(icon, android.widget.LinearLayout.LayoutParams(180, 180))
        val title = TextView(this).apply {
            text = "InstaFab"
            textSize = 30f
            setTextColor(android.graphics.Color.rgb(23, 32, 30))
            gravity = android.view.Gravity.CENTER
        }
        root.addView(title)
        val brand = TextView(this).apply {
            text = "By PaliaAPK HUB"
            textSize = 15f
            setTextColor(android.graphics.Color.rgb(24, 184, 154))
            gravity = android.view.Gravity.CENTER
        }
        root.addView(brand)
        val developer = TextView(this).apply {
            text = "Developer by shanpalia"
            textSize = 13f
            setTextColor(android.graphics.Color.rgb(104, 115, 111))
            gravity = android.view.Gravity.CENTER
        }
        root.addView(developer)
        setContentView(root)
        Handler(Looper.getMainLooper()).postDelayed({ showHome() }, 1200)
    }

    private fun showHome() {
        setContentView(R.layout.activity_main)
        val input = findViewById<EditText>(R.id.searchInput)
        val button = findViewById<Button>(R.id.searchButton)
        val status = findViewById<TextView>(R.id.statusText)

        button.setOnClickListener {
            val query = input.text.toString().trim()
            status.text = if (query.isEmpty()) {
                "Enter a username or public profile link."
            } else {
                "No profile data is displayed until a real, permitted public data source is connected.\n\nInstaFab does not use fake or demo profile data."
            }
        }
    }
}
