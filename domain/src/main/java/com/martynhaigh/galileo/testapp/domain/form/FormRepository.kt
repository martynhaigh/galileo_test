package com.martynhaigh.galileo.testapp.domain.form

import com.martynhaigh.galileo.testapp.domain.entities.DataEntity
import com.martynhaigh.galileo.testapp.domain.form.entity.FormEntity
import kotlinx.coroutines.channels.ReceiveChannel

interface FormRepository {
    suspend fun getFormData(): ReceiveChannel<DataEntity<FormEntity>>
}