package com.example.redis_accedir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import redis.clients.jedis.Jedis
import java.awt.font.TextAttribute

class MainActivity : AppCompatActivity() {

    internal var claus: List<String>? = null


    private var sqlThread: Thread = object : Thread() {
        override fun run() {

            val con = Jedis("89.36.214.106")
            con.connect()
            con.auth("ieselcaminas.ad")

            claus = con.keys("*").sorted()
            con.close()


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Des de la versió 3 d'Android, no es permet obrir una connexió des del thread principal.
        // Per tant s'ha de crear un nou.
        sqlThread.start()

        // i ara esperem a que finalitze el thread fill unint-lo (join)
        try {
            sqlThread.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, claus!!)
        spinner.adapter = adapter

    }
}
