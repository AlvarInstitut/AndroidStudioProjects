package com.example.coffeeshopskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class CardsActivity : AppCompatActivity() {

    private var items: ArrayList<Tarjeta>? = null

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

        val db = Room.databaseBuilder(
            applicationContext,
            LocalDatabase::class.java, "CoffeeShops.sqlite"
        ).build()

        var locals = db.localDao().getLocals()

        for (l in locals)
            items!!.add(Tarjeta(l.imatge!!, l.num.toString()!!, l.nom!!))


        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val recView = findViewById(R.id.recView) as RecyclerView
        recView.setHasFixedSize(true)
        val adaptador = CardsAdapter(items!!)

        recView.adapter = adaptador
        recView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}