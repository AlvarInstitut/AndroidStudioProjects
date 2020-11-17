package com.example.coffeeshops_fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ComentsAdapter(private val items: ArrayList<Comentario>) : RecyclerView.Adapter<ComentsAdapter.TarjViewHolder>() {

    class TarjViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val texto: TextView

        init {
            texto = itemView.findViewById(R.id.cita)
        }

        fun bindComentario(c: Comentario ) {
            texto.text = c.comen1
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TarjViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_coment, viewGroup, false)
        return TarjViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: TarjViewHolder, pos: Int) {
        val item = items[pos]
        viewHolder.bindComentario(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}