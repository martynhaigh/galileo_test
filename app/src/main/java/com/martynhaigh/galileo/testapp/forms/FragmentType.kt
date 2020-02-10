package com.martynhaigh.galileo.testapp.forms

import com.martynhaigh.galileo.testapp.base.BaseFragment
import com.martynhaigh.galileo.testapp.forms.batchquestions.BatchQuestionsFragment
import com.martynhaigh.galileo.testapp.forms.genericquestions.GenericQuestionsFragment
import com.martynhaigh.galileo.testapp.forms.medicationpecificquestions.MedicationSpecificQuestionsFragment
import com.martynhaigh.galileo.testapp.forms.medications.MedicationsFragment
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
sealed class FragmentType {
    object MedicationList : FragmentType() {
        override fun create(): BaseFragment {
            return MedicationsFragment.newInstance()
        }
    }

    object GenericForm : FragmentType() {
        override fun create(): BaseFragment {
            return GenericQuestionsFragment.newInstance()
        }
    }

    object BatchForm : FragmentType() {
        override fun create(): BaseFragment {
            return BatchQuestionsFragment.newInstance()
        }
    }

    object MedicationSpecificForm : FragmentType() {
        override fun create(): BaseFragment {
            return MedicationSpecificQuestionsFragment.newInstance()
        }
    }

    abstract fun create(): BaseFragment
}