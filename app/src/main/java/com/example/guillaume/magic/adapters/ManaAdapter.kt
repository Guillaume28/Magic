package com.example.guillaume.magic.adapters

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.guillaume.magic.Config
import com.example.guillaume.magic.services.ManaInferrerService

open class ManaAdapter {
    fun generateManaCost(ctx: Context, manaCost: String?): List<View> {
        return ManaInferrerService
                .parseManaCost(manaCost)
                .map { this.generateManaView(ctx, it) }
    }

    private fun generateManaView(ctx: Context, manaCost: Int): View {
        return if (manaCost in Config.manaRange) {
            val textView = TextView(ctx)
            textView.text = manaCost.toString()
            textView
        } else {
            val imageView = ImageView(ctx)
            imageView.setImageResource(manaCost)
            imageView
        }
    }
}