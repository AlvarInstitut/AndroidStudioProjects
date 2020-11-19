package com.example.coffeeshops_fragments_room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var comentarios: ArrayList<Comentario>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        comentarios = ArrayList()

        val root = inflater.inflate(R.layout.fragment_second, container, false)
        val texto: TextView =  root.findViewById(R.id.textview_second)

        val l = arguments?.get("local") as LocalsAmbComentaris
        texto.text = l.local.nom
        for (c in l.coms)
            comentarios.add(Comentario(c.comentari!!))

        val recView: RecyclerView = root.findViewById(R.id.recyclerview_coment)
        recView.setHasFixedSize(true)
        val adaptador = ComentsAdapter(comentarios)

        recView.adapter = adaptador
        recView.layoutManager = GridLayoutManager(context, 2)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

/*        view.findViewById<Button>(R.id.button_second).setOnClickListener {
           findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }*/
    }
}
