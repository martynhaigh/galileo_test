package com.martynhaigh.galileo.testapp.data.form.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FormModel(
    @Json(name = "batch_form")
    val batchForm: BatchFormModel,
    @Json(name = "generic_form")
    val genericForm: GenericFormModel,
    @Json(name = "medication_specific_forms")
    val medicationSpecificForms: Map<String, MedicationSpecificFormModel>?,
    @Json(name = "medications")
    val medications: Map<String, MedicationModel>
)

@JsonClass(generateAdapter = true)
data class BatchFormModel(
    @Json(name = "questions")
    val questions: List<QuestionModel>
)

@JsonClass(generateAdapter = true)
data class QuestionModel(
    @Json(name = "answers")
    val answers: List<AnswerModel>? = null,
    @Json(name = "id")
    val id: String,
    @Json(name = "text")
    val text: String,
    @Json(name = "type")
    val type: String
)

@JsonClass(generateAdapter = true)
data class AnswerModel(
    @Json(name = "id")
    val id: String,
    @Json(name = "text")
    val text: String
)

@JsonClass(generateAdapter = true)
data class GenericFormModel(
    @Json(name = "questions")
    val questions: List<QuestionModel>
)

@JsonClass(generateAdapter = true)
data class MedicationSpecificFormsModel(
    val medicationSpecificForms: Map<String, MedicationSpecificFormModel>
)

@JsonClass(generateAdapter = true)
data class MedicationSpecificFormModel(
    @Json(name = "questions")
    val questions: List<QuestionModel>
)

@JsonClass(generateAdapter = true)
data class MedicationsModel(
    val medicationList: Map<String, MedicationModel>
)

@JsonClass(generateAdapter = true)
data class MedicationModel(
    @Json(name = "name")
    val name: String
)
