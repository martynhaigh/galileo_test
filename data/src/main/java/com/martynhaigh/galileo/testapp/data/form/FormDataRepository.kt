package com.martynhaigh.galileo.testapp.data.form

import android.content.Context
import com.martynhaigh.galileo.testapp.data.form.model.FormModel
import com.martynhaigh.galileo.testapp.domain.entities.DataEntity
import com.martynhaigh.galileo.testapp.domain.entities.ErrorEntity
import com.martynhaigh.galileo.testapp.domain.form.FormRepository
import com.martynhaigh.galileo.testapp.domain.form.entity.FormEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import java.io.IOException


class FormDataRepository(
    private val moshi: Moshi,
    private val context: Context,
    private val filename: String
) : FormRepository {

    lateinit var formEntity: FormEntity

    override suspend fun getFormData(): ReceiveChannel<DataEntity<FormEntity>> {
        return GlobalScope.produce {
            launch {
                if (::formEntity.isInitialized) {
                    send(DataEntity.SUCCESS(formEntity))
                } else {
                    send(DataEntity.LOADING())

                    val adapter: JsonAdapter<FormModel> = moshi.adapter(FormModel::class.java)
                    try {
                        val myjson =
                            context.assets.open(filename).bufferedReader().use { it.readText() }

                        val data = adapter.fromJson(myjson)
                        if (data != null) {
                            formEntity = data.toEntity()
                            send(DataEntity.SUCCESS(formEntity))
                        } else {
                            send(DataEntity.ERROR(ErrorEntity("Error parsing JSON")))
                        }
                    } catch (e: IOException) {
                        send(DataEntity.ERROR(ErrorEntity("Error parsing JSON")))
                    }
                }
            }
        }
    }
}
