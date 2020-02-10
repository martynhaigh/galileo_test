package com.martynhaigh.galileo.testapp.domain.form.usecase

import com.martynhaigh.galileo.testapp.domain.entities.DataEntity
import com.martynhaigh.galileo.testapp.domain.form.entity.QuestionEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetGenericQuestionsUseCase(
    private val getFormDataUseCase: GetFormDataUseCase
) {
    suspend fun getGenericFormQuestions(): Flow<DataEntity<List<QuestionEntity>>> {
        return getFormDataUseCase.getFormData().map {
            when (it) {
                is DataEntity.LOADING -> {
                    DataEntity.LOADING()
                }
                is DataEntity.ERROR -> {
                    DataEntity.ERROR<List<QuestionEntity>>(it.error)

                }
                is DataEntity.SUCCESS -> {
                    DataEntity.SUCCESS(it.data?.genericQuestions)
                }
            }
        }
    }
}