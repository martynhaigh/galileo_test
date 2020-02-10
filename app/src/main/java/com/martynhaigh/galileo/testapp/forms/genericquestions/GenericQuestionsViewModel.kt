package com.martynhaigh.galileo.testapp.forms.genericquestions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.martynhaigh.galileo.testapp.base.BaseViewModel
import com.martynhaigh.galileo.testapp.domain.entities.DataEntity
import com.martynhaigh.galileo.testapp.domain.form.entity.MedicationEntity
import com.martynhaigh.galileo.testapp.domain.form.entity.QuestionEntity
import com.martynhaigh.galileo.testapp.domain.form.usecase.GetGenericQuestionsUseCase
import com.martynhaigh.galileo.testapp.domain.form.usecase.GetMedicationsUseCase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class GenericQuestionsViewModel(
    private val getMedicationsUseCase: GetMedicationsUseCase,
    private val getGenericQuestionsUseCase: GetGenericQuestionsUseCase
) : BaseViewModel() {
    var questionLiveData: MutableLiveData<DataEntity<List<QuestionEntity>>> = MutableLiveData()
    var medicationLiveData: MutableLiveData<DataEntity<List<MedicationEntity>>> = MutableLiveData()

    fun fetchData() {
        viewModelScope.launch {
            getGenericQuestionsUseCase.getGenericFormQuestions().collect {
                questionLiveData.value = it
            }
            getMedicationsUseCase.getMedications().collect {
                medicationLiveData.value = it
            }
        }
    }
}
