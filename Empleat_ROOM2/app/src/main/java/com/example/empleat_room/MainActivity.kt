package com.example.empleat_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var cont: String = ""

    private var sqlThread: Thread = object : Thread() {
        override fun run() {
            val db = Room.databaseBuilder(
                    applicationContext,
                    EmpleatsDatabase::class.java, "Empleats.sqlite"
            )
                    .createFromAsset("Empleats.sqlite")
                    .build()

            val e1 = Empleat(5,"Elena",10,25, 2500.0.toFloat())
            db.empleatsDao().insert(e1)

            var emps = db.empleatsDao().getEmpleats()

            for (e in emps)
                cont+=e.nom + " (" + e.sou + ")\n"

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sqlThread.start()
        // i ara esperem a que finalitze el thread fill unint-lo (join)
        sqlThread.join()

        text.setText(cont)
    }
}