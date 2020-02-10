package com.martynhaigh.galileo.testapp.forms.batchquestions

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.martynhaigh.galileo.testapp.domain.form.entity.QuestionEntity

abstract class BatchBaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(question: QuestionEntity)
}