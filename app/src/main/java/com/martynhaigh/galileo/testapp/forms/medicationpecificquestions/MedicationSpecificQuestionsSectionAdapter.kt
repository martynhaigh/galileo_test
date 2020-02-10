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
import kotlinx.android.synthetic.main.fragment_generic_questions.*

class MedicationSpecificQuestionsSectionAdapter :
    RecyclerView.Adapter<MedicationSpecificSectionBaseViewHolder>() {

    private var items: List<Pair<String, List<QuestionEntity>>> = emptyList()
    var itemList: Map<String, List<QuestionEntity>> = emptyMap()
        set(value) {
            field = value
            items = value.toList()
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MedicationSpecificSectionBaseViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_medication_specific_section, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : MedicationSpecificSectionBaseViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var list: RecyclerView = itemView.findViewById(R.id.question_list)
        var adapter = MedicationSpecificQuestionsAdapter()

        override fun bind(data: Pair<String, List<QuestionEntity>>) {
            title.text = data.first
            list.layoutManager = LinearLayoutManager(list.context)
            val dividerItemDecoration = DividerItemDecoration(list.context, LinearLayout.HORIZONTAL)
            list.addItemDecoration(dividerItemDecoration)
            list.adapter = adapter
            adapter.questionList = data.second
        }
    }

    override fun onBindViewHolder(holder: MedicationSpecificSectionBaseViewHolder, position: Int) {
        holder.bind(items[position])

    }
}
