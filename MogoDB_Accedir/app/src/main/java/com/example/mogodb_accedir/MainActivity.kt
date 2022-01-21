package com.example.mogodb_accedir

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import kotlinx.android.synthetic.main.activity_main.*
import org.bson.Document
import com.mongodb.DBCursor
import com.mongodb.client.FindIterable







class MainActivity : AppCompatActivity() {
    internal var cont: String = ""


    private var sqlThread: Thread = object : Thread() {
        override fun run() {

            val con = MongoClient(MongoClientURI("mongodb://ad:ieselcaminas@89.36.214.106/?authSource=test"))
            val bd = con.getDatabase("test")

            val ordenar = Document()
            ordenar.put("precio", -1)

            val llibres = bd.getCollection("libro").find()
            //val docs: FindIterable<Document> = bd.getCollection("libro").find()
//            val llibre = llibres.first()
//            cont += llibre.get("titulo").toString() + " --> " + llibre.get("precio")
            for (llibre in llibres)
                cont += llibre.get("titulo").toString() + " --> " + llibre.get("precio")

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

        text.setText(cont)

    }
}