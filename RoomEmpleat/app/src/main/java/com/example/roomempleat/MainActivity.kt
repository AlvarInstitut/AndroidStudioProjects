package com.example.roomempleat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    internal var cont: String = ""


    private var sqlThread: Thread = object : Thread() {
        override fun run() {
            val db = Room.databaseBuilder(
                applicationContext,
                EmpleatDatabase::class.java, "Empleats.sqlite"
            ).build()

            //val e1 = Empleat(5,"Elena",10,25,2500.0)
           // db.empleatDao().insert(e1)
            var emps = db.empleatDao().getEmpleats()


            cont = "Comença llistat: " + emps.size + " empleats\n"

            for (e in emps)
                cont+=e.nom + " (" + e.sou + ")\n"

            cont += "Finalitza llistat"


        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sqlThread.start()

        // i ara esperem a que finalitze el thread fill unint-lo (join)
        try {
            sqlThread.join()
        } catch (e: InterruptedException) {
            println("Sembla que ha anat malament")
            e.printStackTrace()
        }

        text.setText(cont)
    }
}