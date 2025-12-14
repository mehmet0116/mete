package com.metegelistirme.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.metegelistirme.databinding.ItemLessonCardBinding
import com.metegelistirme.ui.models.Lesson

class LessonAdapter(
    private val items: List<Lesson>,
    private val onClick: (Lesson) -> Unit
) : RecyclerView.Adapter<LessonAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemLessonCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(lesson: Lesson) {
            binding.lessonTitle.text = lesson.title
            binding.lessonDescription.text = lesson.description
            binding.lessonTitle.setTextColor(ContextCompat.getColor(itemView.context, lesson.colorRes))
            
            binding.card.setOnClickListener { 
                onClick(lesson) 
            }
            binding.btnStartLesson.setOnClickListener {
                onClick(lesson)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLessonCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}
