package com.instafab.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var platform = "Instagram"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showSplash()
    }

    private fun showSplash() {
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({ showHome() }, 1800)
    }

    private fun showHome() {
        setContentView(R.layout.activity_main)
        setupHome()
    }

    private fun setupHome() {
        val status = findViewById<TextView>(R.id.statusText)
        val input = findViewById<EditText>(R.id.searchInput)

        val instagram = findViewById<TextView>(R.id.platformInstagram)
        val facebook = findViewById<TextView>(R.id.platformFacebook)
        val tiktok = findViewById<TextView>(R.id.platformTikTok)
        val twitter = findViewById<TextView>(R.id.platformTwitter)
        val platforms = listOf(instagram, facebook, tiktok, twitter)

        fun select(name: String) {
            platform = name
            platforms.forEach { view ->
                view.setBackgroundResource(if (view.text.toString() == name || (name == "Twitter / X" && view == twitter)) R.drawable.chip_selected else R.drawable.chip_outline)
                view.setTextColor(if (view.text.toString() == name || (name == "Twitter / X" && view == twitter)) 0xFFFFFFFF.toInt() else 0xFF20232B.toInt())
            }
            status.text = "$name selected. Enter a username or public profile link."
        }

        instagram.setOnClickListener { select("Instagram") }
        facebook.setOnClickListener { select("Facebook") }
        tiktok.setOnClickListener { select("TikTok") }
        twitter.setOnClickListener { select("Twitter / X") }

        findViewById<TextView>(R.id.searchButton).setOnClickListener {
            val raw = input.text.toString().trim()
            if (raw.isEmpty()) {
                input.requestFocus()
                status.text = "Enter a username or public profile link first."
                Toast.makeText(this, "Enter a username or profile link", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val url = buildProfileUrl(platform, raw)
            if (url == null) {
                status.text = "That profile link could not be understood."
                return@setOnClickListener
            }
            status.text = "Opening $platform public profile…"
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }

        findViewById<TextView>(R.id.actionProfile).setOnClickListener {
            input.requestFocus()
            status.text = "Profile Viewer selected. Search a public username above."
        }
        findViewById<TextView>(R.id.actionVideo).setOnClickListener {
            status.text = "Video Downloader selected. Paste a permitted public media URL when supported."
            Toast.makeText(this, "Video Downloader selected", Toast.LENGTH_SHORT).show()
        }
        findViewById<TextView>(R.id.actionPhoto).setOnClickListener {
            status.text = "Photo Downloader selected. Paste a permitted public media URL when supported."
            Toast.makeText(this, "Photo Downloader selected", Toast.LENGTH_SHORT).show()
        }
        findViewById<TextView>(R.id.actionHistory).setOnClickListener {
            status.text = "Download History is ready for downloads made by InstaFab."
        }

        findViewById<TextView>(R.id.navHome).setOnClickListener { status.text = "Home" }
        findViewById<TextView>(R.id.navDownloads).setOnClickListener { status.text = "Downloads — no downloads yet." }
        findViewById<TextView>(R.id.navSearch).setOnClickListener { input.requestFocus(); status.text = "Search selected. Enter a username above." }
        findViewById<TextView>(R.id.navHistory).setOnClickListener { status.text = "History — no download history yet." }
        findViewById<TextView>(R.id.navSettings).setOnClickListener { status.text = "Settings — platform: $platform" }
    }

    private fun buildProfileUrl(selectedPlatform: String, value: String): String? {
        if (value.startsWith("http://") || value.startsWith("https://")) return value
        val username = value.removePrefix("@").trim().replace(" ", "")
        if (username.isEmpty()) return null
        return when (selectedPlatform) {
            "Instagram" -> "https://www.instagram.com/$username/"
            "Facebook" -> "https://www.facebook.com/$username"
            "TikTok" -> "https://www.tiktok.com/@$username"
            "Twitter / X" -> "https://x.com/$username"
            else -> null
        }
    }
}
