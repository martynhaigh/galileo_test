package com.martynhaigh.galileo.testapp.forms.medicationpecificquestions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.martynhaigh.galileo.testapp.R
import com.martynhaigh.galileo.testapp.domain.form.entity.QuestionEntity
import com.martynhaigh.galileo.testapp.forms.base.MultipleQuestionAdapter
import com.martynhaigh.galileo.testapp.forms.base.QuestionType
import kotlinx.android.synthetic.main.fragment_generic_questions.*

class MedicationSpecificQuestionsAdapter :
    RecyclerView.Adapter<MedicationSpecificBaseViewHolder>() {

    var questionList: List<QuestionEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return questionList.size
    }

    override fun getItemViewType(position: Int): Int =
        QuestionType.parse(questionList[position].type).viewId

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MedicationSpecificBaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            QuestionType.FreeText.viewId ->
                FreeTextViewHolder(
                    layoutInflater.inflate(
                        viewType,
                        parent,
                        false
                    )
                )

            QuestionType.MultipleChoice.viewId ->
                MultipleChoiceViewHolder(
                    layoutInflater.inflate(
                        viewType,
                        parent,
                        false
                    )
                )

            else -> throw IllegalStateException("Unexpected item type")
        }
    }

    class FreeTextViewHolder(itemView: View) : MedicationSpecificBaseViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        override fun bind(question: QuestionEntity) {
            title.text = question.text
        }

    }

    class MultipleChoiceViewHolder(itemView: View) : MedicationSpecificBaseViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var list: RecyclerView = itemView.findViewById(R.id.question_list)
        var adapter: MultipleQuestionAdapter =
            MultipleQuestionAdapter()

        override fun bind(question: QuestionEntity) {
            title.text = question.text
            list.layoutManager = LinearLayoutManager(list.context)
            val dividerItemDecoration = DividerItemDecoration(list.context, LinearLayout.HORIZONTAL)
            list.addItemDecoration(dividerItemDecoration)
            list.adapter = adapter
            adapter.itemList = question.answers!!
        }
    }

    override fun onBindViewHolder(holder: MedicationSpecificBaseViewHolder, position: Int) {
        holder.bind(
            questionList[position]
        )
    }
}

