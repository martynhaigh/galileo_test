package com.martynhaigh.galileo.testapp.domain.form.usecase

import com.martynhaigh.galileo.testapp.domain.form.FormRepository
import com.martynhaigh.galileo.testapp.domain.form.entity.FormEntity
import com.martynhaigh.galileo.testapp.domain.entities.DataEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow

class GetFormDataUseCase(
    private val repository: FormRepository
) {
    suspend fun getFormData(): Flow<DataEntity<FormEntity>> {
        return repository.getFormData().consumeAsFlow()
    }
}