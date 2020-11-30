package com.example.coffeeshops_fragments_room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.coffeeshops_fragments_room.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var comments: List<Comment> = ArrayList<Comment>()
    var adaptador = CommentsAdapter(ArrayList<Comment>())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentSecondBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_second, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = CoffeeShopsDatabase.getInstance(application).coffeeShopsDao
        val viewModelFactory = SecondFragmentViewModelFactory(dataSource, application)
        val secondFragmentViewModel = ViewModelProvider(this, viewModelFactory).get(SecondFragmentViewModel::class.java)
        binding.secondFragmentViewModel = secondFragmentViewModel
        binding.lifecycleOwner = this

        binding.recyclerviewComent.setHasFixedSize(true)
        val adaptador = CommentsAdapter(comments)
        binding.recyclerviewComent.adapter = adaptador
        binding.recyclerviewComent.layoutManager = GridLayoutManager(context, 2)

        secondFragmentViewModel.coffeWitlAllComments.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.textviewSecond.text = it.first().coffee.title
            comments = it.first().coms
            adaptador.items = comments
            adaptador.notifyDataSetChanged()
            //binding.recyclerviewComent.invalidate()
        })

        val args = SecondFragmentArgs.fromBundle(requireArguments())
        val idCoffe = args.idCoffe

        secondFragmentViewModel.getAllComments(idCoffe)

        return binding.root
    }

}