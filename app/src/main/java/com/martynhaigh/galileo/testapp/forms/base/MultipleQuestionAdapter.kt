package com.martynhaigh.galileo.testapp.forms.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.martynhaigh.galileo.testapp.R
import com.martynhaigh.galileo.testapp.domain.form.entity.AnswerEntity


class MultipleQuestionAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var itemList: List<AnswerEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_multiple_choice_question_item, parent, false)
        return ViewHolder(
            view
        )
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var item: CheckBox = itemView.findViewById(R.id.checkbox)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = (holder as ViewHolder).item
        item.text = itemList[position].text
    }
}