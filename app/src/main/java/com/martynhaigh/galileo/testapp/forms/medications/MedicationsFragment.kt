package com.martynhaigh.galileo.testapp.forms.medications

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
import kotlinx.android.synthetic.main.fragment_medications.*
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

@InternalCoroutinesApi
class MedicationsFragment : BaseFragment() {

    private lateinit var adapter: MedicationsAdapter

    companion object {
        fun newInstance() = MedicationsFragment()
    }

    private val viewModel: MedicationsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_medications, container, false)

    override fun retrieveQuestionsAndAnswers(): List<QuestionAndAnswer>? {
        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.layoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(list.context, LinearLayout.HORIZONTAL)
        list.addItemDecoration(dividerItemDecoration)
        adapter = MedicationsAdapter()
        list.adapter = adapter

        viewModel.fetchData()
    }

    override fun onStart() {
        super.onStart()
        viewModel.liveData.observe {
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
