package com.martynhaigh.galileo.testapp.domain.form.entity

data class FormEntity(
    val batchQuestions: List<QuestionEntity>,
    val genericQuestions: List<QuestionEntity>,
    val medicationSpecificQuestions: Map<String, List<QuestionEntity>>?,
    val medications: List<MedicationEntity>
)

data class QuestionEntity(
    val answers: List<AnswerEntity>? = null,
    val id: String,
    val text: String,
    val type: String
)

data class AnswerEntity(
    val id: String,
    val text: String
)

data class MedicationEntity(
    val id: String,
    val name: String
)
