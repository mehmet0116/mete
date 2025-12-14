package com.metegelistirme.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.metegelistirme.R
import com.metegelistirme.ui.models.Lesson

class LessonAdapter(
    private val items: List<Lesson>,
    private val onClick: (Lesson) -> Unit
) : RecyclerView.Adapter<LessonAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleText: TextView = itemView.findViewById(R.id.titleText)
        private val descriptionText: TextView = itemView.findViewById(R.id.descriptionText)
        private val moduleIcon: ImageView = itemView.findViewById(R.id.moduleIcon)
        private val card: MaterialCardView = itemView.findViewById(R.id.card)

        fun bind(lesson: Lesson) {
            titleText.text = lesson.title
            descriptionText.text = lesson.description
            titleText.setTextColor(ContextCompat.getColor(itemView.context, lesson.colorRes))

            // Set icon if it exists
            try {
                moduleIcon.setImageResource(lesson.iconRes ?: R.drawable.ic_education)
            } catch (e: Exception) {
                moduleIcon.setImageResource(R.drawable.ic_education)
            }

            card.setOnClickListener {
                onClick(lesson)
            }
            itemView.setOnClickListener {
                onClick(lesson)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lesson_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
