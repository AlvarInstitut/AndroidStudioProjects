package com.example.coffeeshops_fragments_room

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardsAdapter(private val items: ArrayList<Tarjeta>) : RecyclerView.Adapter<CardsAdapter.TarjViewHolder>() {

    lateinit var onClick: (View) -> Unit

    class TarjViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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

        fun bindCards(t: Tarjeta, onClick: (View) -> Unit) {
            //imagen.setImageResource(t.imag)
            val img = t.imatge
            val imgBmp = BitmapFactory.decodeByteArray(img, 0, img.size)
            imagen.setImageBitmap(imgBmp)
            text.text = t.nom
            text1.text = t.adreca
            barstars.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { ratingBar: RatingBar, fl: Float, b: Boolean ->
                    puntos.text = fl.toString()
                }
            itemView.setOnClickListener{ onClick(itemView) }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TarjViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_cards, viewGroup, false)
        return TarjViewHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: TarjViewHolder, pos: Int) {
        val item = items[pos]
        viewHolder.bindCards(item, onClick)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}