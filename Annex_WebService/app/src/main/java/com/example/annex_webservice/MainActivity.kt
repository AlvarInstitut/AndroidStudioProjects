package com.example.annex_webservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    internal var cont: String = ""

    private var sqlThread: Thread = object : Thread() {
        override fun run() {
            val connection = URL("http://192.168.1.101:41062/www/rutes_2.php").openConnection() as HttpURLConnection
            val dades = connection.inputStream.bufferedReader().readText()
            val rutes = JSONTokener(dades).nextValue() as JSONArray

            for (i in 0 until rutes.length()){
                val r = rutes.get(i) as JSONObject
                cont += ("Ruta " + r.get("num_r").toString() + ": " + r.get("nom_r")
                        .toString() + ". Desnivell: " + r.get("desn")
                        .toString() + ". Desnivell Acumulat: " + r.get("desn_acum")  + "\n")
            }
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

        text.setText(cont)

    }
}