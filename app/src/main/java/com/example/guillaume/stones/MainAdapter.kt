package com.example.guillaume.stones

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_row.view.*

class MainAdapter : RecyclerView.Adapter<CustomViewHolder>() {

    private val cards = mutableListOf<Card>()

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.card_row, parent, false)

        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val card = cards[position]
        val view = holder.view
        Picasso.get()
                .load(card.imageUrl)
                .placeholder(R.drawable.ic_mtg_logo)
                .into(view.imageView)
        view.textView_music_title.text = card.text
        view.textView_card_title.text = card.name

        val imageView = generateManaCost(view.context, card.manaCost)
        view.layout_ManaCost.addView(imageView)
    }

    fun addItems(cards: List<Card>): MainAdapter{
        this.cards += cards
        return this
    }

    private fun inferManaType(manaCost: String): Int{
        return when(manaCost){
            "{W}" -> R.drawable.ic_white_mana
            "{R}" -> R.drawable.ic_red_mana
            "{B}" -> R.drawable.ic_black_mana
            "{G}" -> R.drawable.ic_green_mana
            "{U}" -> R.drawable.ic_blue_mana
            else -> R.drawable.ic_unknow
        }
    }

    private fun generateManaCost(ctx: Context, manaCost: String): ImageView{
        val imageView = ImageView(ctx)
        val drawable = inferManaType(manaCost)
        imageView.setImageResource(drawable)
        return imageView
    }

//    private fun parseManaCost(manaCost: String): ImageView{
//        val mana =
//    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view)