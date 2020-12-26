package com.example.provaspinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        area.append("Ja va" + "\n")
        val opcions = arrayListOf<String>("Primer", "Segon", "Tercer")
        val adaptador =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, opcions)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        combo.adapter = adaptador

  /*      combo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
                println("Rien de rien")
            }

            override fun onItemSelected(arg0: AdapterView<*>, arg1: View, arg2: Int, arg3: Long) {
                println(arg2)
                area.append(arg2.toString())
                area.append("Entrant....." + combo.getSelectedItemPosition())

            }
        }
*/
        combo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                area.append(position.toString() + "\n")
            }

        }
    }
}