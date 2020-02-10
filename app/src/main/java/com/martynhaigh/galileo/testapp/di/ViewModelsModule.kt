package com.martynhaigh.galileo.testapp.di

import com.martynhaigh.galileo.testapp.forms.MainViewModel
import com.martynhaigh.galileo.testapp.forms.batchquestions.BatchQuestionsViewModel
import com.martynhaigh.galileo.testapp.forms.genericquestions.GenericQuestionsViewModel
import com.martynhaigh.galileo.testapp.forms.medicationpecificquestions.MedicationSpecificQuestionsViewModel
import com.martynhaigh.galileo.testapp.forms.medications.MedicationsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
val viewModelsModule = module {
    viewModel {
        MainViewModel()
    }
    viewModel {
        MedicationsViewModel(get())
    }
    viewModel {
        GenericQuestionsViewModel(get(), get())
    }
    viewModel {
        BatchQuestionsViewModel(get())
    }
    viewModel {
        MedicationSpecificQuestionsViewModel(get())
    }
}
