package com.martynhaigh.galileo.testapp.forms.medicationpecificquestions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.martynhaigh.galileo.testapp.R
import com.martynhaigh.galileo.testapp.base.BaseFragment
import com.martynhaigh.galileo.testapp.domain.entities.DataEntity
import com.martynhaigh.galileo.testapp.forms.uimodel.QuestionAndAnswer
import kotlinx.android.synthetic.main.fragment_generic_questions.*
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

@InternalCoroutinesApi
class MedicationSpecificQuestionsFragment : BaseFragment() {

    private var adapter = MedicationSpecificQuestionsSectionAdapter()

    companion object {
        fun newInstance() = MedicationSpecificQuestionsFragment()
    }

    private val viewModel: MedicationSpecificQuestionsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_generic_questions, container, false)

    override fun retrieveQuestionsAndAnswers(): List<QuestionAndAnswer>? {
        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.layoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(list.context, LinearLayout.HORIZONTAL)
        list.addItemDecoration(dividerItemDecoration)
        list.adapter = adapter

        viewModel.fetchData()
    }

    override fun onStart() {
        super.onStart()
        viewModel.questionLiveData.observe {
            when (it) {
                is DataEntity.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    adapter.itemList = it.data!!
                }
                is DataEntity.ERROR -> {
                    progressBar.visibility = View.GONE
                    // TODO : Show error view
                }
                is DataEntity.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        }
    }
}
