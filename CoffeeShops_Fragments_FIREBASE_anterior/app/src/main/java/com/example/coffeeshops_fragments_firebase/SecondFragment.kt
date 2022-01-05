package com.example.coffeeshops_fragments_firebase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var Comments = arrayListOf<Comment>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()

        val root = inflater.inflate(R.layout.fragment_second, container, false)
        val texto: TextView =  root.findViewById(R.id.textview_second)
        val coffee = arguments?.get("coffee") as Coffee
        val nameDoc = arguments?.get("doc") as String
        texto.text = coffee.title
        db.collection("CoffeeShops").document(nameDoc).collection("comentaris").addSnapshotListener { snapshots, e ->
            for (dc in snapshots!!.documentChanges) {
                when (dc.type) {
                    DocumentChange.Type.ADDED -> {
                        Comments.add(Comment(dc.document.getString("comentari")))
                    }
                }
            }
        }

        val recView: RecyclerView = root.findViewById(R.id.recyclerview_coment)
        recView.setHasFixedSize(true)
        db.collection("CoffeeShops").document(nameDoc).collection("comentari").
                get().addOnSuccessListener {
            val adaptador = CommentsAdapter(Comments)

            recView.adapter = adaptador
            recView.layoutManager = GridLayoutManager(context, 2)

        }
        val adaptador = CommentsAdapter(Comments)

        recView.adapter = adaptador
        recView.layoutManager = GridLayoutManager(context, 2)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      /*  view.findViewById<Button>(R.id.button_second).setOnClickListener {
           findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }*/
    }
}