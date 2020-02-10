package com.martynhaigh.galileo.testapp.forms.medicationpecificquestions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.martynhaigh.galileo.testapp.base.BaseViewModel
import com.martynhaigh.galileo.testapp.domain.entities.DataEntity
import com.martynhaigh.galileo.testapp.domain.form.entity.QuestionEntity
import com.martynhaigh.galileo.testapp.domain.form.usecase.GetMedicationSpecificQuestionsUseCase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class MedicationSpecificQuestionsViewModel(
    private val getMedicationSpecificQuestionsUseCase: GetMedicationSpecificQuestionsUseCase
) :
    BaseViewModel() {
    var questionLiveData: MutableLiveData<DataEntity<Map<String, List<QuestionEntity>>>> =
        MutableLiveData()

    fun fetchData() {
        viewModelScope.launch {
            getMedicationSpecificQuestionsUseCase.getGenericFormQuestions().collect {
                questionLiveData.value = it
            }
        }
    }
}
