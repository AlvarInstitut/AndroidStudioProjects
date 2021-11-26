package com.example.connexiodb4o

import Adreca
import Empleat
import Telefon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.db4o.Db4oEmbedded
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text = findViewById(R.id.text) as TextView

        val f = File("/data/data/com.example.connexiodb4o/databases")

        if (!f.exists()) {
            f.mkdirs()
            val bd = Db4oEmbedded.openFile("/data/data/com.example.connexiodb4o/databases/Empleats.db4o")
            var e1 = Empleat("11111111a", "Albert", 10, 40, 1000.0, null, null, null, null, null)
            //les dades més complicades les introduïm de forma especial
            e1.adreca=Adreca("C/ Major, 7", "12001", "Castelló")
            val corr = arrayOf("alu11111111a@ieselcaminas.org")
            e1.correus_e = corr
            val tels = arrayOf(Telefon(true, "666777888"), Telefon(false, "964112233"))
            e1.telefons = tels
            val e2 = Empleat("22222222b", "Berta", 10, 35, 1700.0, null, null, null, null, null)
            val e3 = Empleat("33333333c", "Clàudia", 20, 37, 1500.0, null, null, null, null, null)
            text.append("S'ha creat la Base de Dades Orientada a Objectes")
            bd.store(e1)
            bd.store(e2)
            bd.store(e3)
            bd.close()
        } else {
            val bd1 = Db4oEmbedded.openFile("/data/data/com.example.connexiodb4o/databases/Empleats.db4o")
            val llista = bd1.queryByExample<Empleat>(Empleat(null))
            text.append("S'han trobat " + llista.size + " empleats: \n")
            for (e in llista) {
                text.append("Nif: " + e.nif + ". Nom: " + e.nom + ". Departament: " + e.departament + "\n")
                text.append("\tLlista de telèfons:\n")
                if (e.telefons != null) {
                    for (t in e.telefons!!) {
                        text.append("\t\t" + t.numero)
                        if (t.mobil!!)
                            text.append("(mòbil)\n")
                        else
                            text.append("(fixe)\n")
                    }
                }
            }
            bd1.close()
        }

    }
}
