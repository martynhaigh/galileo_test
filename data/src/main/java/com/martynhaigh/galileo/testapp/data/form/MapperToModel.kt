package com.martynhaigh.galileo.testapp.data.form

import com.martynhaigh.galileo.testapp.data.form.model.*
import com.martynhaigh.galileo.testapp.domain.form.entity.AnswerEntity
import com.martynhaigh.galileo.testapp.domain.form.entity.FormEntity
import com.martynhaigh.galileo.testapp.domain.form.entity.MedicationEntity
import com.martynhaigh.galileo.testapp.domain.form.entity.QuestionEntity

internal fun FormModel.toEntity(): FormEntity =
    FormEntity(
        batchQuestions = batchForm.toEntity(),
        genericQuestions = genericForm.toEntity(),
        medicationSpecificQuestions = medicationSpecificForms?.toEntities(),
        medications = medications.toEntities()
    )

@JvmName("MedicationModel")
private fun Map<String, MedicationModel>.toEntities(): List<MedicationEntity> =
    this.toList().map { MedicationEntity(id = it.first, name = it.second.name) }

@JvmName("MedicationSpecificFormModel")
private fun Map<String, MedicationSpecificFormModel>?.toEntities():
        Map<String, List<QuestionEntity>>? = this?.mapValues { it.value.questions.toEntities() }

private fun GenericFormModel.toEntity(): List<QuestionEntity> = questions.toEntities()

private fun BatchFormModel.toEntity(): List<QuestionEntity> = questions.toEntities()

private fun List<QuestionModel>.toEntities(): List<QuestionEntity> = this.map { it.toEntity() }

private fun QuestionModel.toEntity(): QuestionEntity =
    QuestionEntity(answers = answers?.toEntity(), id = id, text = text, type = type)

private fun List<AnswerModel>?.toEntity(): List<AnswerEntity>? =
    this?.map { AnswerEntity(id = it.id, text = it.text) }

