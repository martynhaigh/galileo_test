package com.martynhaigh.galileo.testapp.forms

import com.martynhaigh.galileo.testapp.base.BaseFragment
import com.martynhaigh.galileo.testapp.forms.uimodel.QuestionAndAnswer
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class FragmentNode(private val type: FragmentType) {

    var next: FragmentNode? = null
    private var questionAndAnswers: List<QuestionAndAnswer> = emptyList()

    fun storeResults(questionAndAnswers: List<QuestionAndAnswer>) {
        this.questionAndAnswers = questionAndAnswers
    }

    fun create(): BaseFragment {
        return type.create()
    }
}