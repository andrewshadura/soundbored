package com.bri1.soundbored2

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.*
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // rimshot
        val rimshot = findViewById<ImageButton>(R.id.rimshot)
        val mpDrum: MediaPlayer = MediaPlayer.create(this, R.raw.rimshot)
        rimshot.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mpDrum.start()
            }
        })

        // crickets
        val crickets = findViewById<ImageButton>(R.id.crickets)
        val mpCrickets: MediaPlayer = MediaPlayer.create(this, R.raw.crickets)
        crickets.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mpCrickets.start()
            }
        })

        // trombone
        val trombone = findViewById<ImageButton>(R.id.trombone)
        val mpTrombone: MediaPlayer = MediaPlayer.create(this, R.raw.trombone)
        trombone.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mpTrombone.start()
            }
        })

        // whistle
        val whistle = findViewById<ImageButton>(R.id.whistle)
        val mpWhistle: MediaPlayer = MediaPlayer.create(this, R.raw.whistledesc)
        whistle.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mpWhistle.start()
            }
        })

        // nope
        val nope = findViewById<ImageButton>(R.id.nope)
        val mpNope: MediaPlayer = MediaPlayer.create(this, R.raw.nope)
        nope.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mpNope.start()
            }
        })

        // chicken nugget
        val chickenNug = findViewById<ImageButton>(R.id.chicken_nugget)
        val mpChickenNug: MediaPlayer = MediaPlayer.create(this, R.raw.nug)
        chickenNug.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mpChickenNug.start()
            }
        })

        // chipotle
        val chipotle = findViewById<ImageButton>(R.id.chipotle)
        val mpChipotle: MediaPlayer = MediaPlayer.create(this, R.raw.chipotle)
        chipotle.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                mpChipotle.start()
            }
        })

//        val headBang = findViewById<ImageButton>(R.id.headbang)
//        val mpHeadBang: MediaPlayer = MediaPlayer.create(this, R.raw.headbang)
//        headBang.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                mpHeadBang.start()
//            }
//        })
//
//        val imJohnCena = findViewById<ImageButton>(R.id.im_john_cena)
//        val mpJohnCena: MediaPlayer = MediaPlayer.create(this, R.raw.imjohncena)
//        imJohnCena.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//                mpJohnCena.start()
//            }
//        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)

        with (Intent(Intent.ACTION_SEND)) {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text))
            menu.findItem(R.id.action_share).intent = this
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
//            R.id.action_share -> {
//                Toast.makeText(applicationContext, "click on share", Toast.LENGTH_LONG).show()
//                true
//            }
            R.id.action_credits ->{
                this.startActivity(Intent(this,Credits::class.java))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
