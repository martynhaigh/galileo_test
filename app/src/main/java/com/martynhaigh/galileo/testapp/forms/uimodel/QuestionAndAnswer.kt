package com.martynhaigh.galileo.testapp.forms.uimodel

import com.martynhaigh.galileo.testapp.domain.form.entity.QuestionEntity

sealed class QuestionAndAnswer(private val questionEntity: QuestionEntity) {
    class FreeQuestionAndAnswer(questionEntity: QuestionEntity) :
        QuestionAndAnswer(questionEntity) {
        val answer: String? = null
    }

    class MultipleAnswerQuestionAndAnswer(questionEntity: QuestionEntity) :
        QuestionAndAnswer(questionEntity) {
        val answers: List<String> = emptyList()
    }

}