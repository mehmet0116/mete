package com.metegelistirme.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.metegelistirme.R
import com.metegelistirme.models.Game

class GamesAdapter(
    private val games: List<Game>,
    private val onGameClick: (Game) -> Unit
) : RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {

    inner class GameViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val gameTitle: TextView = itemView.findViewById(R.id.gameTitle)
        private val gameDescription: TextView = itemView.findViewById(R.id.gameDescription)
        private val gameIcon: ImageView = itemView.findViewById(R.id.gameIcon)
        private val card: MaterialCardView = itemView as MaterialCardView

        fun bind(game: Game) {
            gameTitle.text = game.title
            gameDescription.text = game.description
            gameIcon.setImageResource(game.iconResId)
            card.setCardBackgroundColor(ContextCompat.getColor(itemView.context, game.backgroundColor))

            itemView.setOnClickListener {
                onGameClick(game)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game_card, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(games[position])
    }

    override fun getItemCount(): Int = games.size
}

