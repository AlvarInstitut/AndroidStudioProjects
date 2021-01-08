package com.example.coffeeshops_fragments_firebase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var items: ArrayList<Coffee> = ArrayList()
    private var itemsDocs: ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_first, container, false)
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()

        if (items.size==0) {  // per a no executar-lo una altra vegada quan tornem del SecondFragment
            val storageRef: StorageReference;
            storageRef = FirebaseStorage.getInstance().getReference();

            db.collection("CoffeeShops").addSnapshotListener { snapshots, e ->
                for (dc in snapshots!!.documentChanges) {
                    when (dc.type) {
                        DocumentChange.Type.ADDED -> {
                            var p = 0
                            if (dc.document.getString("punts")!="")
                                p = dc.document.get("punts").toString().toInt()

                            items!!.add(Coffee(dc.document.getString("nom").toString(),
                                dc.document.getString("adreca").toString(),
                                p, null
                            ))
                            itemsDocs.add(dc.document.id)
                            val item = items[items.size-1]
                            val nomImatge = dc.document.getString("imatge")

                            if (nomImatge != null) {
                                val imatgeRef = storageRef.child(nomImatge)
                                val ONE_MEGABYTE = (1024 * 1024).toLong()
                                imatgeRef.getBytes(ONE_MEGABYTE)
                                        .addOnSuccessListener(OnSuccessListener<ByteArray> {
                                            // Data for "images/island.jpg" is returns, use this as needed
                                            item.image=it
                                        }).addOnFailureListener(OnFailureListener {
                                            // Handle any errors
                                        })
                            }

                        }
                    }
                }
            }
        }

        db.collection("CoffeeShops").get().addOnSuccessListener {
            val recView: RecyclerView = root.findViewById(R.id.recView)
            recView.setHasFixedSize(true)
            val adaptador = CoffeeAdapter(items)
            recView.adapter = adaptador
            recView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            adaptador.onClick = {
                val c = items[recView.getChildAdapterPosition(it)]
                val d = itemsDocs[recView.getChildAdapterPosition(it)]
                val bundle = bundleOf("coffee" to c,"doc" to d)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
            }
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        }
}