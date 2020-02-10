package com.martynhaigh.galileo.testapp.forms.medications

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.martynhaigh.galileo.testapp.base.BaseViewModel
import com.martynhaigh.galileo.testapp.domain.entities.DataEntity
import com.martynhaigh.galileo.testapp.domain.form.entity.MedicationEntity
import com.martynhaigh.galileo.testapp.domain.form.usecase.GetMedicationsUseCase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class MedicationsViewModel(private val getMedicationsUseCase: GetMedicationsUseCase) :
    BaseViewModel() {
    var liveData: MutableLiveData<DataEntity<List<MedicationEntity>>> = MutableLiveData()

    fun fetchData() {
        viewModelScope.launch {
            getMedicationsUseCase.getMedications().collect {
                liveData.value = it
            }
        }
    }
}
