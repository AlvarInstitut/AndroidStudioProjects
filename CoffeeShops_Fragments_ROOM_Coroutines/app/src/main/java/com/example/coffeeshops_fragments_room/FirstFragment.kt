package com.example.coffeeshops_fragments_room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coffeeshops_fragments_room.databinding.FragmentFirstBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    var adaptador = CoffeeAdapter(ArrayList<Coffee>())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        val binding: FragmentFirstBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_first, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = CoffeeShopsDatabase.getInstance(application).coffeeShopsDao
        val viewModelFactory = FirstFragmentViewModelFactory(dataSource, application)
        val firstFragmentViewModel = ViewModelProvider(this, viewModelFactory).get(FirstFragmentViewModel::class.java)
        binding.firstFragmentViewModel = firstFragmentViewModel
        binding.lifecycleOwner = this

        binding.recView.setHasFixedSize(true)
        binding.recView.adapter = adaptador
        binding.recView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        firstFragmentViewModel.allCoffees.observe(viewLifecycleOwner, Observer {
            adaptador.items = it
            adaptador.notifyDataSetChanged()
            //binding.recView.invalidate()
        })

        firstFragmentViewModel.getAllCoffees()

        adaptador.onClick = {
            val coffe = adaptador.items[binding.recView.getChildAdapterPosition(it)]
            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(coffe.num ?: 0))
        }
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        }
}