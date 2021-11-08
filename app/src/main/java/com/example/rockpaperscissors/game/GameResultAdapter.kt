package com.example.rockpaperscissors.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.database.GameResult

class GameResultAdapter :
    ListAdapter<GameResult, GameResultAdapter.ViewHolder>(GameResultDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        val res = holder.itemView.context.resources //.text = "${position.plus(1)}. $item"
        holder.gameResult.text = item.result
        holder.gameScore.text = item.score
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.list_item_game_result, parent, false)
        return ViewHolder(view)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val gameResult: TextView = itemView.findViewById(R.id.gameResultText)
        val gameScore: TextView = itemView.findViewById(R.id.gameScoreText)
    }
}

class GameResultDiffCallback : DiffUtil.ItemCallback<GameResult>() {
    override fun areItemsTheSame(oldItem: GameResult, newItem: GameResult): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GameResult, newItem: GameResult): Boolean {
        return oldItem == newItem
    }
}

