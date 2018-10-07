package com.example.guillaume.stones

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.music_row.view.*

class MainAdapter: RecyclerView.Adapter<CustomViewHolder>() {

    val musicTitles = listOf("First title", "Second", "Third", "caca")

    //Number of items
    override fun getItemCount(): Int {
        return musicTitles.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.music_row, parent, false)

        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val musicTitle = musicTitles[position]
        holder.view.textView_music_title.text = musicTitle
    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}