package com.example.coffeeshops_fragments_firebase

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CoffeeAdapter(private val items: ArrayList<Coffee>) : RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder>() {

    lateinit var onClick: (View) -> Unit

    class CoffeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView
        private val text: TextView
        private val text1: TextView
        private val barstars: RatingBar
        private val points: TextView

        init {

            image = itemView.findViewById(R.id.img1)
            text = itemView.findViewById(R.id.textView)
            text1 = itemView.findViewById(R.id.textView1)
            points = itemView.findViewById(R.id.textView2)
            barstars = itemView.findViewById(R.id.ratingBar)
        }

        fun bindCards(t: Coffee, onClick: (View) -> Unit) {
            //image.setImageResource(t.image)
            val img = t.image
            if (img != null) {
                val imgBmp = BitmapFactory.decodeByteArray(img, 0, img.size)
                image.setImageBitmap(imgBmp)
            }
            text.text = t.title
            text1.text = t.subtitle
            if (t.points!=null) {
                barstars.rating = t.points.toFloat()
                points.text=t.points.toString()
            }
            barstars.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { ratingBar: RatingBar, fl: Float, b: Boolean ->
                    points.text = fl.toString()
                }
            itemView.setOnClickListener{ onClick(itemView) }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CoffeeViewHolder {
        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_coffee, viewGroup, false)
        return CoffeeViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(viewHolder: CoffeeViewHolder, pos: Int) {
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