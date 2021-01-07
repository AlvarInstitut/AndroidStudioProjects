package com.example.t7ex3_mastermind_rd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class Jugada(var nom: String, var tirada: String, var colocades: String, var desordenades: String)

class MainActivity : AppCompatActivity() {
    var secret =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseDatabase.getInstance()
        val mm = db.getReference("Mastermind")
        val tirades = mm.child("tirades")
        val secMaster = mm.child("secret")
        val finalitzada = mm.child("finalitzada")

        // Comprovació de si la partida està en marxa
        mm.addListenerForSingleValueEvent(
            object : ValueEventListener {
                override
                fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.getValue() == null || dataSnapshot.child("finalitzada").getValue() as Boolean) {
                        novaPartida()
                    } else {
                        secret = dataSnapshot.child("secret").getValue() as String
                    }
                }

                override
                fun onCancelled(error: DatabaseError) {
                }
            }
        )

        // Visualització de jugades
        tirades.addChildEventListener(
            object : ChildEventListener {
                override
                fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                    textView.append(
                        dataSnapshot.child("nom").getValue().toString() + ": "
                                + dataSnapshot.child("tirada").getValue().toString() + " "
                                + dataSnapshot.child("colocades").getValue().toString() + " "
                                + dataSnapshot.child("desordenades").getValue().toString() + "\n"
                    )
                    if (dataSnapshot.child("colocades").getValue().toString() == "4")
                        finalPartida()
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

        boto.setOnClickListener { enviar() }

        botoNova.setOnClickListener { novaPartida() }

    }


    fun enviar() {
        val db = FirebaseDatabase.getInstance()
        val mm = db.getReference("Mastermind")
        val tirades = mm.child("tirades")
        val comp = comprova(tirada.getText().toString(), secret)

        tirades.push()
            .setValue(Jugada(jugador.getText().toString(), tirada.getText().toString(), comp[0].toString(), comp[1].toString()), null)
        tirada.setText("")
        tirada.requestFocus()
    }

    fun novaPartida() {
        textView.setText("")
        val db = FirebaseDatabase.getInstance()
        val mm = db.getReference("Mastermind")
        val finalitzada = mm.child("finalitzada")
        val tirades = mm.child("tirades")
        // He d'assegurar-me que algun altre no l'ha ficada en marxa
        mm.addListenerForSingleValueEvent(
                object : ValueEventListener {
                    override
                    fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.child("finalitzada").getValue() as Boolean) {
                            finalitzada.setValue(false, null)
                            secret = genera()
                            mm.child("secret").setValue(secret, null)
                            tirades.setValue(null, null)

                        } else {
                            secret = dataSnapshot.child("secret").getValue() as String
                        }
                    }

                    override
                    fun onCancelled(error: DatabaseError) {
                    }
                }
        )

        tirada.setVisibility(View.VISIBLE)
        boto.setVisibility(View.VISIBLE)
        botoNova.setVisibility(View.INVISIBLE)

    }

    fun finalPartida() {
        textView.append("---   Solució Trobada   ---\n")
        textView.append("--- Partida Finalitzada ---")
        botoNova.setVisibility(View.VISIBLE)
        boto.setVisibility(View.INVISIBLE)
        tirada.setVisibility(View.INVISIBLE)
        val finalitzada = FirebaseDatabase.getInstance().getReference("Mastermind/finalitzada")
        finalitzada.setValue(true,null)
    }

    fun genera(): String {
        val i0 = (Math.random() * 10).toInt()
        var i1 = (Math.random() * 10).toInt()
        while (i1 == i0)
            i1 = (Math.random() * 10).toInt()
        var i2 = (Math.random() * 10).toInt()
        while (i2 == i0 || i2 == i1)
            i2 = (Math.random() * 10).toInt()
        var i3 = (Math.random() * 10).toInt()
        while (i3 == i0 || i3 == i1 || i3 == i2)
            i3 = (Math.random() * 10).toInt()
        return (i0.toString() + i1.toString() + i2.toString() + i3.toString())
    }

    fun comprova(num: String, sec: String): IntArray {
        var pos = 0
        var nopos = 0
        for (i in 0..3)
            for (j in 0..3)
                if (num[i] == sec[j])
                    if (i == j) pos++
                    else nopos++
        return intArrayOf(pos, nopos)
    }
}