package com.example.tema7_5_cloudstorage_1

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FileDownloadTask
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mStorageRef = FirebaseStorage.getInstance().getReference()
        mStorageRef.listAll().addOnSuccessListener {
            val opcions = ArrayList<String>()
            for (f in it.items) {
                opcions.add(f.name)
            }
            val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcions)
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            nom.adapter = adaptador

        }

        // Amb getFile()
        nom.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(arg0:AdapterView<*>, arg1: View, arg2:Int, arg3:Long) {
                val imgRef = mStorageRef.child(nom.selectedItem.toString())
                val localFile = File.createTempFile("images", "jpg")
                imgRef.getFile(localFile)
                        .addOnSuccessListener(OnSuccessListener<FileDownloadTask.TaskSnapshot?> {
                            // Successfully downloaded data to local file
                            val bm = BitmapFactory.decodeFile(localFile.getAbsolutePath())
                            imatge.setImageBitmap(bm)
                        }).addOnFailureListener(OnFailureListener {
                            // Handle failed download
                            // ...
                        })
            }
            override fun onNothingSelected(arg0:AdapterView<*>) {
                // TODO Auto-generated method stub
            }
        }

        // Amb getBytes()
/*
        nom.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(arg0:AdapterView<*>, arg1: View, arg2:Int, arg3:Long) {
                val imgRef = mStorageRef.child(nom.selectedItem.toString())
                val localFile = File.createTempFile("images", "jpg")
            imgRef.getBytes(500000)
                .addOnSuccessListener(OnSuccessListener<ByteArray?> {
                    // Successfully downloaded data to local file
                    val bm = BitmapFactory.decodeByteArray(it, 0, it!!.size)
                    imatge.setImageBitmap(bm)
                }).addOnFailureListener(OnFailureListener {
                    // Handle failed download
                    // ...
                })
            }
            override fun onNothingSelected(arg0:AdapterView<*>) {
                // TODO Auto-generated method stub
            }
        }
*/

    }
}
