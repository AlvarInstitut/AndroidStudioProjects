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
import androidx.room.Room

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var items: ArrayList<Tarjeta>
    private lateinit var localsAmbComentaris: List<LocalsAmbComentaris>
    private var sqlThread: Thread = object : Thread() {
        override fun run() {
            val db = Room.databaseBuilder(
                requireActivity().baseContext,
                CoffeeShopsDatabase::class.java, "CoffeeShops.sqlite"
            ).build()

            localsAmbComentaris = db.localDao().getLocals()

            for (l in localsAmbComentaris)
                items!!.add(Tarjeta(l.local.imatge!!, l.local.nom!!, l.local.adreca!!))


        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_first, container, false)
        items = ArrayList()
/*        items.add(Tarjeta(R.drawable.images, "Antico Caffè Greco", "St. Italy, Rome"))
        items.add(Tarjeta(R.drawable.images1, "Coffee Room", "St. Germany, Berlin "))
        items.add(Tarjeta(R.drawable.images2, "Coffee Ibiza", "St. Colon, Madrid"))
        items.add(Tarjeta(R.drawable.images3, "Pudding Coffee Shop", "St. Diagonal, Barcelona"))
        items.add(Tarjeta(R.drawable.images4, "L'Express", "St. Picadilly Circus, London"))
        items.add(Tarjeta(R.drawable.images5, "Coffee Corner", "St. Àngel Guimerà, Valencia"))
        items.add(Tarjeta(R.drawable.images6, "Sweet Cup", "St.Kinkerstraat, Amsterdam"))
*/
        sqlThread.start()

        // i ara esperem a que finalitze el thread fill unint-lo (join)
        try {
            sqlThread.join()
        } catch (e: InterruptedException) {
            println("Sembla que ha anat malament")
            e.printStackTrace()
        }
        val recView: RecyclerView = root.findViewById(R.id.recView)
        recView.setHasFixedSize(true)
        val adaptador = CardsAdapter(items)

        recView.adapter = adaptador
        recView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        adaptador.onClick = {
            val t = items[recView.getChildAdapterPosition(it)]
            val l = localsAmbComentaris[recView.getChildAdapterPosition(it)]
/*            var coments = ""
            for (c in l.coms)
		        coments += c.comentari + "\n"
            val bundle = bundleOf("local" to l.local.nom,"coments" to coments)*/
            val bundle = bundleOf("local" to l)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundle)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        }
}


