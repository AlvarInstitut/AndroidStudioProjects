package com.example.t7ex4_audioscf_cs

import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pantPrincipal = this  // per a utilitzar-lo en el ArrayAdapter del spinner

        // Inicialització de la referència i altres variables
        val db = FirebaseFirestore.getInstance()
        val colRef = db.collection("Audios")

        val mStorageRef = FirebaseStorage.getInstance().getReference()

        // Lectura de tots els audios per a posar-les després al Spinner
        // S'hauran de llegir tots els documents de la col·lecció, per tant addSnapshotListener
        // sobre la col·lecció
        val llistaAudios = ArrayList<String>()
        val llistaFitxers = ArrayList<String>()
        colRef.addSnapshotListener { snapshots, e ->
            for (dc in snapshots!!.documentChanges) {
                llistaAudios.add(dc.document.getString("nom")!!)
                llistaFitxers.add(dc.document.getString("fitxer")!!)
            }

        }

        // Inicialització del Spinner, amb un ArrayAdapter passant-li la llista d'audios
        // es recomana fer-ho dins d'un addOnSuccessListener sobre la mateixa col·lecció
        // per assegurar-nos que el HashSet ja té les províncies
        colRef.get().addOnSuccessListener { dataSnapshot ->
            val adaptador =
                    ArrayAdapter(pantPrincipal, android.R.layout.simple_spinner_item, llistaAudios)
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adaptador
        }

        // Instruccions per a posar en el TextView les dades de la provincia seleccionada
        // Dins del onItemSelectedListener del Spinner també s'hauran de llegir els documents de la col·lecció
        // que són de la província triada, per tant una altra vegada addSnapshotListener
        var mediaPlayer = MediaPlayer()
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val audioRef = mStorageRef.child(llistaFitxers[position])
                val localFile = File.createTempFile("audio", "temp")
                val iStream:InputStream = FileInputStream(localFile)
                audioRef.getFile(localFile)
                        .addOnSuccessListener(OnSuccessListener<FileDownloadTask.TaskSnapshot?> {
                            // Successfully downloaded data to local file
                            //val myUri: Uri = Uri.parse(localFile.name)
                            //mediaPlayer=MediaPlayer.create(pantPrincipal, localFile.toUri())
                            //mediaPlayer.prepare()
                            mediaPlayer.reset()
                            //mediaPlayer.setDataSource(localFile)
                            //mediaPlayer.stop()

                            // SI QUE FUNCIONA
                            val inputStream = FileInputStream(localFile)
                            mediaPlayer.setDataSource(inputStream.fd)
                            inputStream.close()
                            mediaPlayer.prepareAsync()

                        }).addOnFailureListener(OnFailureListener {
                            // Handle failed download
                            // ...
                        })

            }

        }



        play.setOnClickListener {
            //mediaPlayer?.seekTo(0)
            //mediaPlayer.prepare()
            mediaPlayer.start() }
        stop.setOnClickListener {
            mediaPlayer.stop()
            mediaPlayer.prepareAsync()
            //mediaPlayer?.seekTo(0)

        }

    }
}