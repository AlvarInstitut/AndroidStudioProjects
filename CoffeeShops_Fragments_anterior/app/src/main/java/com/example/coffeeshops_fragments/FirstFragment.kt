package com.example.coffeeshops_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var items: ArrayList<Coffee>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_first, container, false)
        items = ArrayList()
        items.add(Coffee("Antico Caffè Greco", "St. Italy, Rome",R.drawable.images))
        items.add(Coffee( "Coffee Room", "St. Germany, Berlin ",R.drawable.images1))
        items.add(Coffee( "Coffee Ibiza", "St. Colon, Madrid",R.drawable.images2))
        items.add(Coffee("Pudding Coffee Shop", "St. Diagonal, Barcelona",R.drawable.images3))
        items.add(Coffee("L'Express", "St. Picadilly Circus, London",R.drawable.images4))
        items.add(Coffee("Coffee Corner", "St. Àngel Guimerà, Valencia",R.drawable.images5))
        items.add(Coffee("Sweet Cup", "St.Kinkerstraat, Amsterdam",R.drawable.images6))

        val recView: RecyclerView = root.findViewById(R.id.recView)
        recView.setHasFixedSize(true)
        val adaptador = CoffeeAdapter(items)

        recView.adapter = adaptador
        recView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        adaptador.onClick = {
            val t = items[recView.getChildAdapterPosition(it)]
            val bundle = bundleOf("NOM" to t.title)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        }
}


