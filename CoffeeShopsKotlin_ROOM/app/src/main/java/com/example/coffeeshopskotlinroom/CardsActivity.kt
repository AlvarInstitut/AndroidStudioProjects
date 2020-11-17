package com.example.coffeeshopskotlinroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.coffeeshopskotlin.R

class CardsActivity : AppCompatActivity() {

    private var items: ArrayList<Tarjeta>? = null

    private var sqlThread: Thread = object : Thread() {
        override fun run() {
            val db = Room.databaseBuilder(
                applicationContext,
                LocalDatabase::class.java, "coffeeshops.sqlite"
            ).build()

            var locals = db.localDao().getLocals()

            for (l in locals)
                items!!.add(Tarjeta(l.imatge!!, l.num.toString()!!, l.nom!!))


        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards)

        items = ArrayList<Tarjeta>()
        /*items!!.add(Tarjeta(R.drawable.images, "Antico Caffè Greco", "St. Italy, Rome"))
        items!!.add(Tarjeta(R.drawable.images1, "Coffee Room","St. Germany, Berlin "))
        items!!.add(Tarjeta(R.drawable.images2, "Coffee Ibiza","St. Colon, Madrid"))
        items!!.add(Tarjeta(R.drawable.images3, "Pudding Coffee Shop", "St. Diagonal, Barcelona"))
        items!!.add(Tarjeta(R.drawable.images4, "L'Express", "St. Picadilly Circus, London"))
        items!!.add(Tarjeta(R.drawable.images5, "Coffee Corner","St. Àngel Guimerà, Valencia"))
        items!!.add(Tarjeta(R.drawable.images6, "Sweet Cup","St.Kinkerstraat, Amsterdam")) */

/*        val db = Room.databaseBuilder(
            applicationContext,
            LocalDatabase::class.java, "coffeeshops.sqlite"
        ).build()

        var locals = db.localDao().getLocals()

        for (l in locals)
            items!!.add(Tarjeta(l.imatge!!, l.num.toString()!!, l.nom!!))

*/

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val recView = findViewById(R.id.recView) as RecyclerView
        recView.setHasFixedSize(true)
        sqlThread.start()

        // i ara esperem a que finalitze el thread fill unint-lo (join)
        try {
            sqlThread.join()
        } catch (e: InterruptedException) {
            println("Sembla que ha anat malament")
            e.printStackTrace()
        }

        val adaptador = CardsAdapter(items!!)

        recView.adapter = adaptador
        recView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }
}