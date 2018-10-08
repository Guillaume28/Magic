package com.example.guillaume.stones

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                .placeholder(R.drawable.ic_placeholder)
                .into(view.imageView)
        view.textView_music_title.text = card.text
        view.textView_card_title.text = card.name
    }

    fun addItems(cards: List<Card>): MainAdapter{
        this.cards += cards
        return this
    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view)