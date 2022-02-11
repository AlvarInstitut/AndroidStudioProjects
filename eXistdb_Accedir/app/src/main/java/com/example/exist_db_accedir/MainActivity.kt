package com.example.exist_db_accedir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_main.*
import net.xqj.exist.ExistXQDataSource

class MainActivity : AppCompatActivity() {
    internal var cont: String = ""


    private var sqlThread: Thread = object : Thread() {
        override fun run() {

            val s = ExistXQDataSource()
            s.setProperty("serverName", "89.36.214.106")
            val conn = s.getConnection()
            println("Connexió feta")
            val sent = "for \$alumne in doc(\"/db/Tema9/classe.xml\")//alumne order by \$alumne/cognoms return \$alumne"

            val cons = conn.prepareExpression(sent)
            val rs = cons.executeQuery()

            while (rs.next())
                  cont += rs.getItemAsString(null) + "\n"
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