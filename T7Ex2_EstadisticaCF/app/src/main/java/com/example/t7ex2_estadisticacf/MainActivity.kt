package com.example.t7ex2_estadisticacf

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pantPrincipal = this  // per a utilitzar-lo en el ArrayAdapter del spinner

        // Inicialització de la referència i altres variables
        val db = FirebaseFirestore.getInstance()
        val colRef = db.collection("Estadistica")

        // Lectura de totes les províncies per a posar-les després al Spinner
        // S'hauran de llegir tots els documents de la col·lecció, per tant addSnapshotListener
        // sobre la col·lecció
        val llistaProvs = HashSet<String>()
        colRef.addSnapshotListener { snapshots, e ->
            for (dc in snapshots!!.documentChanges) {
                llistaProvs.add(dc.document.getString("Provincia")!!)
            }

        }

        // Inicialització del Spinner, amb un ArrayAdapter passant-li un HashSet ordenat amb les províncies
        // es recomana fer-ho dins d'un addOnSuccessListener sobre la mateixa col·lecció
        // per assegurar-nos que el HashSet ja té les províncies
        colRef.get().addOnSuccessListener { dataSnapshot ->
            val adaptador =
                    ArrayAdapter(pantPrincipal, android.R.layout.simple_spinner_item, llistaProvs.sorted())
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            combo.adapter = adaptador
        }

        // Instruccions per a posar en el TextView les dades de la provincia seleccionada
        // Dins del onItemSelectedListener del Spinner també s'hauran de llegir els documents de la col·lecció
        // que són de la província triada, per tant una altra vegada addSnapshotListener
        combo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                area.setText("")
                colRef.whereEqualTo("Provincia", combo.selectedItem)
                        .orderBy("any").addSnapshotListener { snapshots, e ->
                            for (dc in snapshots!!.documentChanges) {
                                area.append(dc.document.getString("any") + ": " + dc.document.getString("Dones")
                                        + " - " + dc.document.getString("Homes") + "\n")
                            }
                        }

            }

        }
    }
}