package com.martynhaigh.galileo.testapp.domain.form.usecase

import com.martynhaigh.galileo.testapp.domain.entities.DataEntity
import com.martynhaigh.galileo.testapp.domain.form.entity.QuestionEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMedicationSpecificQuestionsUseCase(
    private val getFormDataUseCase: GetFormDataUseCase
) {
    suspend fun getGenericFormQuestions(): Flow<DataEntity<Map<String, List<QuestionEntity>>>> {
        return getFormDataUseCase.getFormData().map {
            when (it) {
                is DataEntity.LOADING -> {
                    DataEntity.LOADING()
                }
                is DataEntity.ERROR -> {
                    DataEntity.ERROR<Map<String, List<QuestionEntity>>>(it.error)

                }
                is DataEntity.SUCCESS -> {
                    val data = it.data
                    val mappedData = data?.medicationSpecificQuestions?.mapKeys { entry ->
                        data.medications.first { medication -> medication.id == entry.key }.name
                    }

                    DataEntity.SUCCESS(mappedData)
                }
            }
        }
    }
}