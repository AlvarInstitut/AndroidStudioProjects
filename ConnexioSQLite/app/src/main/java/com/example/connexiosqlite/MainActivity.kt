package com.example.connexiosqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bdg = SQLiteGestor(this, "Empleats.sqlite", null, 1)
        val bd = bdg.readableDatabase

        val rs = bd.rawQuery("SELECT * FROM EMPLEAT", null)

        var cont =""
        while (rs.moveToNext()) {
            cont += rs.getString(1) + " --> " + rs.getDouble(4).toString() + "€\n"
        }
        text.setText(cont)
        rs.close()
        bd.close()
        bdg.close()
    }
}

