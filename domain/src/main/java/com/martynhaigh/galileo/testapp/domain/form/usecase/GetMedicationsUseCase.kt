package com.martynhaigh.galileo.testapp.domain.form.usecase

import com.martynhaigh.galileo.testapp.domain.entities.DataEntity
import com.martynhaigh.galileo.testapp.domain.form.entity.MedicationEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetMedicationsUseCase(
    private val getFormDataUseCase: GetFormDataUseCase
) {
    suspend fun getMedications(): Flow<DataEntity<List<MedicationEntity>>> {
        return getFormDataUseCase.getFormData().map {
            when (it) {
                is DataEntity.LOADING -> {
                    DataEntity.LOADING()
                }
                is DataEntity.ERROR -> {
                    DataEntity.ERROR<List<MedicationEntity>>(it.error)

                }
                is DataEntity.SUCCESS -> {
                    DataEntity.SUCCESS(it.data?.medications)
                }
            }
        }
    }
}