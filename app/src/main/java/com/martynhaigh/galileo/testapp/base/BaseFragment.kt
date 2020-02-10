package com.martynhaigh.galileo.testapp.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.martynhaigh.galileo.testapp.forms.uimodel.QuestionAndAnswer

abstract class BaseFragment : Fragment() {
    // Method for returning questions and answers for display on final screen
    abstract fun retrieveQuestionsAndAnswers(): List<QuestionAndAnswer>?

    protected fun <T> LiveData<T>.observe(observe: (T?) -> Unit) =
        observe(this@BaseFragment, Observer { observe(it) })
}