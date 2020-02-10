package com.martynhaigh.galileo.testapp.data.di

import android.content.Context
import com.martynhaigh.galileo.testapp.data.form.FormDataRepository
import com.martynhaigh.galileo.testapp.domain.form.FormRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module


val dataModule = module {
    single { createMoshiClient() }
    single(qualifier = StringQualifier("DataFileName")) { "form_data.json" }
}

val repositoryModule = module {
    single {
        FormDataRepository(
            get() as Moshi,
            get() as Context,
            get(StringQualifier("DataFileName"))
        ) as FormRepository
    }
}


fun createMoshiClient(): Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

