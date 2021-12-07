package com.example.t7ex1_estadisticard

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pantPrincipal = this  // per a utilitzar-lo en el ArrayAdapter del Spinner

        // Inicialització de la referència i altres variables
        val database = FirebaseDatabase.getInstance()
        val refEst = database.getReference("EstadisticaVariacioPoblacional")
        val provs = arrayListOf<String>()

        // Lectura de totes les províncies per a posar-les després al Spinner
        // Es recomana llegir els fills de la llista, per tant addChildEventListener
        refEst.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                provs.add(dataSnapshot.child("nombre").getValue(String::class.java)!!)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })

        // Inicialització del Spinner, amb un ArrayAdapter passant-li un ArrayList amb les províncies
        // es recomana fer-ho dins d'un addListenerForSingleValueEvent sobre la mateixa referència
        // per assegurar-nos que l'ArrayList ja té les províncies
        refEst.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val spinnerArrayAdapter = ArrayAdapter<String>(
                    pantPrincipal,
                    android.R.layout.simple_spinner_dropdown_item,
                    provs
                )
                combo.setAdapter(spinnerArrayAdapter)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        // Instruccions per a posar en el TextView les dades de la provincia seleccionada
        // Dins del onItemSelectedListener del Spinner també es recomana llegir els fills de la llista,
        // per tant una altra vegada addChildEventListener
        combo.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
            ) {
                area.text = ""
                val refProvSel = database.getReference("EstadisticaVariacioPoblacional/" + position + "/data")
                refProvSel.addChildEventListener(object: ChildEventListener{
                    override fun onChildAdded(s: DataSnapshot, previousChildName: String?) {
                        area.append("" + s.child("nombrePeriodo").value + ": " + s.child("valor").value + "\n")
                    }

                    override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                        TODO("Not yet implemented")
                    }

                    override fun onChildRemoved(snapshot: DataSnapshot) {
                        TODO("Not yet implemented")
                    }

                    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                        TODO("Not yet implemented")
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // another interface callback
            }
        }
    }
}