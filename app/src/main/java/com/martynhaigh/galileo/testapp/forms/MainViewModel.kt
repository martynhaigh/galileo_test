package com.martynhaigh.galileo.testapp.forms

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.martynhaigh.galileo.testapp.base.BaseFragment
import com.martynhaigh.galileo.testapp.base.BaseViewModel
import com.martynhaigh.galileo.testapp.forms.uimodel.QuestionAndAnswer
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.*

@InternalCoroutinesApi
class MainViewModel : BaseViewModel() {
    val loadFragmentLiveData: MutableLiveData<Fragment> = MutableLiveData()

    fun onNextFragment(lastFragment: Fragment?) {
        (lastFragment as BaseFragment).retrieveQuestionsAndAnswers()
            ?.let { questionsAndAnswers.put(currentNode, it) }
        showNextFragment()
    }

    fun showNextFragment() {
        if (listIterable.hasNext()) {
            showFragment(listIterable.next())
        } else {
            // TODO : Show results fragment
        }
    }

    private fun showFragment(fragmentNode: FragmentNode) {
        currentNode = fragmentNode
        loadFragmentLiveData.value = currentNode.create()
    }

    fun onBackPressed(backStackEntryCount: Int): Boolean {
        return if (backStackEntryCount == 0 || !listIterable.hasPrevious()) {
            false
        } else {
            listIterable.previous()
            true
        }
    }

    private lateinit var currentNode: FragmentNode

    private var list: ArrayList<FragmentNode> = ArrayList()
    private var listIterable: ListIterator<FragmentNode>

    private var questionsAndAnswers: MutableMap<FragmentNode, List<QuestionAndAnswer>> =
        mutableMapOf()

    init {
        list.add(FragmentNode(FragmentType.MedicationList))
        list.add(FragmentNode(FragmentType.GenericForm))
        list.add(FragmentNode(FragmentType.BatchForm))
        list.add(FragmentNode(FragmentType.MedicationSpecificForm))
        listIterable = list.listIterator()
    }
}
