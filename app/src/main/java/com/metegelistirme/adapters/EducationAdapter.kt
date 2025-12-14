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
import com.metegelistirme.models.EducationModule

class EducationAdapter(
    private val modules: List<EducationModule>,
    private val onModuleClick: (EducationModule) -> Unit
) : RecyclerView.Adapter<EducationAdapter.EducationViewHolder>() {

    inner class EducationViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val titleText: TextView = itemView.findViewById(R.id.titleText)
        private val descriptionText: TextView = itemView.findViewById(R.id.descriptionText)
        private val moduleIcon: ImageView = itemView.findViewById(R.id.moduleIcon)
        private val card: MaterialCardView = itemView.findViewById(R.id.card)

        fun bind(module: EducationModule) {
            titleText.text = module.title
            descriptionText.text = module.description
            moduleIcon.setImageResource(module.iconResId)
            card.setCardBackgroundColor(ContextCompat.getColor(itemView.context, module.backgroundColor))

            itemView.setOnClickListener {
                onModuleClick(module)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lesson_card, parent, false)
        return EducationViewHolder(view)
    }

    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {
        holder.bind(modules[position])
    }

    override fun getItemCount(): Int = modules.size
}

