package com.martynhaigh.galileo.testapp.forms.medicationpecificquestions

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.martynhaigh.galileo.testapp.domain.form.entity.QuestionEntity

abstract class MedicationSpecificBaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(question: QuestionEntity)
}