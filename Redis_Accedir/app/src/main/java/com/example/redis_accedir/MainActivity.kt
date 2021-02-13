package com.example.redis_accedir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import redis.clients.jedis.Jedis
import java.awt.font.TextAttribute

class MainActivity : AppCompatActivity() {

    internal var claus: List<String>? = null
    val con = Jedis("89.36.214.106")


    private var sqlThread: Thread = object : Thread() {
        override fun run() {

            con.connect()
            con.auth("ieselcaminas.ad")

            claus = con.keys("*").sorted()
            //con.close()


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

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var altreThread: Thread = object : Thread() {
                    override fun run() {
                        area.setText("")
                        println("Aiiii" + position)
                        val clau = claus?.get(position)
                        println("Aiiii" + clau)
                        val tip = con.type(clau)
                        when (tip){
                            "string" -> area.setText(con.get(clau))
                            "hash" -> {
                                for (camp in con.hkeys(clau))
                                    area.append(camp + " -> " + con.hget(clau,camp) + "\n")
                            }
                            "list" -> {
                                for (camp in con.lrange(clau,0,-1))
                                    area.append(camp + "\n")
                            }
                            "set" -> {
                                for (camp in con.smembers(clau))
                                    area.append(camp + "\n")
                            }
                            "zset" -> {
                                for (camp in con.zrevrangeWithScores(clau,0,-1))
                                    area.append(camp.element + " -> " + camp.score + "\n")
                            }
                        }

                    }
                }
                altreThread.start()
                altreThread.join()
            }
        }

    }


}
