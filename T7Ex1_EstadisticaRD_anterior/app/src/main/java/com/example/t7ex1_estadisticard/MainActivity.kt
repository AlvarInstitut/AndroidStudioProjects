package com.example.t7ex1_estadisticard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.size
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pantPrincipal = this  // per a utilitzar-lo en el ArrayAdapter del spinner

        // Inicialització de la referència i altres variables
        val est = FirebaseDatabase.getInstance().getReference("EstadisticaVariacioPoblacional")
        val opcions = ArrayList<String>()   // l'ArrayList on aniran les províncies

        // Lectura de totes les províncies per a posar-les després al Spinner
        // Es recomana llegir els fills de la llista, per tant addChildEventListener
        est.addChildEventListener(
                object : ChildEventListener {
                    override
                    fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                        opcions.add(dataSnapshot.child("Nombre").getValue().toString())
                    }

                    override
                    fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
                    }

                    override
                    fun onChildRemoved(dataSnapshot: DataSnapshot) {
                    }

                    override
                    fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {
                    }

                    override
                    fun onCancelled(databaseError: DatabaseError) {
                    }
                }
        )

        // Inicialització del Spinner, amb un ArrayAdapter passant-li un ArrayList amb les províncies
        // es recomana fer-ho dins d'un addListenerForSingleValueEvent sobre la mateixa referència
        // per assegurar-nos que l'ArrayList ja té les províncies
        est.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val adaptador =
                        ArrayAdapter(pantPrincipal, android.R.layout.simple_spinner_item, opcions)
                adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                combo.adapter = adaptador
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

        // Instruccions per a posar en el TextView les dades de la provincia seleccionada
        // Dins del onItemSelectedListener del Spinner també es recomana llegir els fills de la llista,
        // per tant una altra vegada addChildEventListener
        combo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                area.setText("")
                val provin = est.child(position.toString()).child("Data")
                provin.addChildEventListener(
                        object : ChildEventListener {
                            override
                            fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                                area.append(dataSnapshot.child("NombrePeriodo").getValue().toString() + ": " +
                                        dataSnapshot.child("Valor").getValue().toString() + "\n"
                                )
                            }

                            override
                            fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
                            }

                            override
                            fun onChildRemoved(dataSnapshot: DataSnapshot) {
                            }

                            override
                            fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {
                            }

                            override
                            fun onCancelled(databaseError: DatabaseError) {
                            }
                        }
                )
            }
        }
    }
}

