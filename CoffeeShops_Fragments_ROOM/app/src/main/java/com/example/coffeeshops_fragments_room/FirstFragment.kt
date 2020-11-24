package com.example.coffeeshops_fragments_room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var items: ArrayList<Coffee> = ArrayList()
    private var itemsWithComments: ArrayList<CoffeeWithComments> = ArrayList()

    private var roomThread: Thread = object : Thread() {
        override fun run() {
            val db = Room.databaseBuilder(
                    context!!,
                    CoffeeShopsDatabase::class.java, "CoffeeShops_CE.sqlite"
            ).createFromAsset("CoffeeShops_CE.sqlite").build()

            items = ArrayList(db.coffeeshopsDao().getCoffees())
            itemsWithComments = ArrayList(db.coffeeshopsDao().getCoffeesWithComments())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_first, container, false)

        if (items.size==0) {  // per a no executar-lo una altra vegada quan tornem del SecondFragment
            roomThread.start()
            roomThread.join()
        }

        val recView: RecyclerView = root.findViewById(R.id.recView)
        recView.setHasFixedSize(true)
        val adaptador = CoffeeAdapter(items)

        recView.adapter = adaptador
        recView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        adaptador.onClick = {
            val c = itemsWithComments[recView.getChildAdapterPosition(it)]
            val bundle = bundleOf("coffee" to c)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        }
}