package com.example.coffeeshops_fragments_room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CommentsAdapter(private val items: ArrayList<Comment>) : RecyclerView.Adapter<CommentsAdapter.CoffeeViewHolder>() {

    class CoffeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val texto: TextView

        init {
            texto = itemView.findViewById(R.id.cita)
        }

        fun bindComment(c: Comment ) {
            texto.text = c.comm
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CoffeeViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_comment, viewGroup, false)
        return CoffeeViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: CoffeeViewHolder, pos: Int) {
        val item = items[pos]
        viewHolder.bindComment(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}