package com.martynhaigh.galileo.testapp.forms.batchquestions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.martynhaigh.galileo.testapp.base.BaseViewModel
import com.martynhaigh.galileo.testapp.domain.entities.DataEntity
import com.martynhaigh.galileo.testapp.domain.form.entity.QuestionEntity
import com.martynhaigh.galileo.testapp.domain.form.usecase.GetBatchQuestionsUseCase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class BatchQuestionsViewModel(
    private val getBatchQuestionsUseCase: GetBatchQuestionsUseCase
) :
    BaseViewModel() {
    var questionLiveData: MutableLiveData<DataEntity<List<QuestionEntity>>> = MutableLiveData()

    fun fetchData() {
        viewModelScope.launch {
            getBatchQuestionsUseCase.getBatchQuestions().collect {
                questionLiveData.value = it
            }
        }
    }
}
