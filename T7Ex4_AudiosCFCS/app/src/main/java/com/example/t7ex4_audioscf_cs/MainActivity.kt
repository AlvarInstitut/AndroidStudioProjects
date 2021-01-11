package com.example.t7ex4_audioscf_cs

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.aquiesnadal)

        play.setOnClickListener { mediaPlayer?.start() }
        stop.setOnClickListener { mediaPlayer?.stop() }

    }
}