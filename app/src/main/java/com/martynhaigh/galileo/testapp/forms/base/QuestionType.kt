package com.martynhaigh.galileo.testapp.forms.base

import com.martynhaigh.galileo.testapp.R

sealed class QuestionType {
    object FreeText : QuestionType() {
        override val viewId: Int
            get() = R.layout.item_free_text_question
    }

    object MultipleChoice : QuestionType() {
        override val viewId: Int
            get() = R.layout.item_multiple_choice_question
    }

    abstract val viewId: Int

    companion object {
        fun parse(text: String): QuestionType =
            when (text) {
                "multi_select" -> MultipleChoice
                "free_text" -> FreeText
                else -> throw IllegalStateException()
            }
    }
}