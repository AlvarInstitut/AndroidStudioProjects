package com.example.exist_db_accedir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import net.xqj.exist.ExistXQDataSource


class MainActivity : AppCompatActivity() {

    internal var claus: ArrayList<String>? = null


    private var sqlThread: Thread = object : Thread() {
        override fun run() {

            val s = ExistXQDataSource()
            s.setProperty("serverName", "89.36.214.106")
            s.setProperty("port", "8080")

            val conn = s.getConnection()
            println("Connexió feta")
            val sent = "//ruta/nom"
            val cons = conn.prepareExpression (sent)
            val rs = cons.executeQuery ()

            while (rs.next())
                claus?.add(rs.getItemAsString(null))

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //System.setProperty("android.javax.xml.stream.XMLInputFactory", "com.sun.xml.stream.ZephyrParserFactory")
        //System.setProperty("android.javax.xml.stream.XMLOutputFactory", "com.sun.xml.stream.ZephyrWriterFactory")
        //System.setProperty("android.javax.xml.stream.XMLEventFactory", "com.sun.xml.stream.events.ZephyrEventFactory")
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
