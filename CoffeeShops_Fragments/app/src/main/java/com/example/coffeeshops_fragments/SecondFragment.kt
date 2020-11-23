package com.example.coffeeshops_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var Comments: ArrayList<Comment>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Comments = ArrayList()
        Comments.add(Comment("Acogedor"))
        Comments.add(Comment("Buenos precios"))
        Comments.add(Comment("Servicio algo flojo"))

        val root = inflater.inflate(R.layout.fragment_second, container, false)
        val texto: TextView =  root.findViewById(R.id.textview_second)
        texto.text = arguments?.getString("NOM")

        val recView: RecyclerView = root.findViewById(R.id.recyclerview_coment)
        recView.setHasFixedSize(true)
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