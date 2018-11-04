package com.example.guillaume.magic

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.guillaume.magic.Config.manaRange
import com.example.guillaume.magic.services.ManaInferrerService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_row.view.*

class MainAdapter : RecyclerView.Adapter<CustomViewHolder>() {

    private val cards = mutableListOf<Card>()

    override fun getItemCount(): Int = cards.size

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
        // TODO fin cleaner way to do this
        view.layout_ManaCost.removeAllViews()
        generateManaCost(view.context, card.manaCost).map { view.layout_ManaCost.addView(it) }
    }


    fun addItems(cards: List<Card>): MainAdapter {
        this.cards += cards
        return this
    }

    private fun generateManaView(ctx: Context, manaCost: Int): View {
        return if (manaCost in manaRange) {
            val textView = TextView(ctx)
            textView.text = manaCost.toString()
            textView
        } else {
            val imageView = ImageView(ctx)
            imageView.setImageResource(manaCost)
            imageView
        }
    }

    private fun generateManaCost(ctx: Context, manaCost: String): List<View> {
        return ManaInferrerService
                .computeManaCost(manaCost)
                .map { this.generateManaView(ctx, it) }
    }

}

class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view)