package com.martynhaigh.galileo.testapp.forms.genericquestions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.martynhaigh.galileo.testapp.R
import com.martynhaigh.galileo.testapp.domain.form.entity.MedicationEntity
import com.martynhaigh.galileo.testapp.domain.form.entity.QuestionEntity
import com.martynhaigh.galileo.testapp.forms.base.MultipleQuestionAdapter
import com.martynhaigh.galileo.testapp.forms.base.QuestionType
import kotlinx.android.synthetic.main.fragment_generic_questions.*

class GenericQuestionsAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    var medicationList: List<MedicationEntity> = emptyList()
        set(value) {
            field = value
            if (value.isNotEmpty() && questionList.isNotEmpty()) {
                notifyDataSetChanged()
            }
        }

    var questionList: List<QuestionEntity> = emptyList()
        set(value) {
            field = value
            if (value.isNotEmpty() && medicationList.isNotEmpty()) {
                notifyDataSetChanged()
            }
        }

    override fun getItemCount(): Int {
        return questionList.size * medicationList.size
    }

    override fun getItemViewType(position: Int): Int =
        QuestionType.parse(questionList[position % questionList.size].type).viewId

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
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

    class FreeTextViewHolder(itemView: View) : BaseViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        override fun bind(question: QuestionEntity, medication: MedicationEntity) {
            title.text = "${medication.name} - ${question.text}"
        }

    }

    class MultipleChoiceViewHolder(itemView: View) : BaseViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var list: RecyclerView = itemView.findViewById(R.id.question_list)
        var adapter: MultipleQuestionAdapter =
            MultipleQuestionAdapter()
        override fun bind(question: QuestionEntity, medication: MedicationEntity) {
            title.text = "${medication.name} - ${question.text}"
            list.layoutManager = LinearLayoutManager(list.context)
            val dividerItemDecoration = DividerItemDecoration(list.context, LinearLayout.HORIZONTAL)
            list.addItemDecoration(dividerItemDecoration)
            list.adapter = adapter
            adapter.itemList = question.answers!!
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(
            questionList[position % questionList.size],
            medicationList[position / questionList.size]
        )
    }
}

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(question: QuestionEntity, medication: MedicationEntity)
}
