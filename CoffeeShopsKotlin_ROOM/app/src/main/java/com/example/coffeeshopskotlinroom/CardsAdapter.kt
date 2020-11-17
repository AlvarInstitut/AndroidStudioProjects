package com.example.coffeeshopskotlinroom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.graphics.BitmapFactory
import com.example.coffeeshopskotlin.R

class CardsAdapter(private val items: ArrayList<Tarjeta>) : RecyclerView.Adapter<CardsAdapter.TarjViewHolder>() {

    inner class TarjViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imagen: ImageView
        private val text: TextView
        private val text1: TextView
        private val barstars: RatingBar
        private val puntos: TextView

        init {

            imagen = itemView.findViewById(R.id.img1)
            text = itemView.findViewById(R.id.textView)
            text1 = itemView.findViewById(R.id.textView1)
            puntos = itemView.findViewById(R.id.textView2)
            barstars = itemView.findViewById(R.id.ratingBar)
        }

        fun bindCards(t: Tarjeta) {
            //imagen.setImageResource(t.imag)
            val img = t.imag
            val imgBmp = BitmapFactory.decodeByteArray(img, 0, img.size)
            imagen.setImageBitmap(imgBmp)
            text.text = t.titulo
            text1.text = t.subtit
            barstars.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { ratingBar, rating, fromUser ->
                puntos.text = rating.toString()
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TarjViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_cards, viewGroup, false)
        return TarjViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: TarjViewHolder, pos: Int) {
        val item = items[pos]
        viewHolder.bindCards(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}