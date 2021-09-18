package com.example.rockpaperscissors.game

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.R

class GameResultAdapter: RecyclerView.Adapter<TextItemViewHolder>() {


    var data = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {

        val layoutInflater  = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.text_item_view, parent, false ) as TextView
        return TextItemViewHolder(view)
    }
}

class TextItemViewHolder(val textView: TextView): RecyclerView.ViewHolder(textView)