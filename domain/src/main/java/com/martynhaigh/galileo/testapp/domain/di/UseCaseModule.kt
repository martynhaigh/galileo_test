package com.martynhaigh.galileo.testapp.domain.di

import com.martynhaigh.galileo.testapp.domain.form.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetFormDataUseCase(get())
    }
    factory {
        GetMedicationsUseCase(get())
    }
    factory {
        GetGenericQuestionsUseCase(get())
    }
    factory {
        GetBatchQuestionsUseCase(get())
    }
    factory {
        GetMedicationSpecificQuestionsUseCase(get())
    }
}

