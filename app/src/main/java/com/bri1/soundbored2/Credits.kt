package com.bri1.soundbored2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView

class Credits : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credits)

        setupHyperlink()
    }

    private fun setupHyperlink() {
        val licenseLink = findViewById<TextView>(R.id.text_license_link)
        licenseLink.setMovementMethod(LinkMovementMethod.getInstance())

        val andrejLink = findViewById<TextView>(R.id.text_andrej)
        andrejLink.setMovementMethod(LinkMovementMethod.getInstance())

        val webcooltzLink = findViewById<TextView>(R.id.text_webcooltz)
        webcooltzLink.setMovementMethod(LinkMovementMethod.getInstance())

        val openSourceLink = findViewById<TextView>(R.id.text_open_source)
        openSourceLink.setMovementMethod(LinkMovementMethod.getInstance())

        val flatIconLink = findViewById<TextView>(R.id.text_icons)
        flatIconLink.setMovementMethod(LinkMovementMethod.getInstance())
    }
}