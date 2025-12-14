package com.metegelistirme.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.metegelistirme.R
import com.metegelistirme.ui.models.GameItem

class GameAdapter(
    private val items: List<GameItem>,
    private val onClick: (GameItem) -> Unit
) : RecyclerView.Adapter<GameAdapter.VH>() {

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: MaterialCardView = itemView.findViewById(R.id.card)
        val title: TextView = itemView.findViewById(R.id.title)
        val subtitle: TextView = itemView.findViewById(R.id.subtitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.title.text = item.title
        holder.subtitle.text = item.subtitle
        holder.card.setCardBackgroundColor(holder.card.context.getColor(item.colorRes))
        holder.card.setOnClickListener { onClick(item) }
    }

    override fun getItemCount() = items.size
}
