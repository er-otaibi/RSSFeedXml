package com.example.rssfeed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.question_row.view.*

class QuestionAdapter(private val myQuestion: ArrayList<Questions>):  RecyclerView.Adapter<QuestionAdapter.ItemViewHolder>(){
    class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.question_row,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val list1 =myQuestion[position]

        holder.itemView.apply {
            tvText.text = list1.title
        }
    }

    override fun getItemCount() = myQuestion.size
}