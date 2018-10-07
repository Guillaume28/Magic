package com.example.guillaume.stones

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.music_row.view.*

class MainAdapter : RecyclerView.Adapter<CustomViewHolder>() {

    private val cards = mutableListOf<Card>()

    //Number of items
    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.music_row, parent, false)

        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val card = cards[position]
        holder.view.textView_music_title.text = card.text
    }

    fun addItems(cards: List<Card>): MainAdapter{
        this.cards += cards
        return this
    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view)