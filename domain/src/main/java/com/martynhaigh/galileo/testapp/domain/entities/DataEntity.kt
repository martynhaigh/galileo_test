package com.martynhaigh.galileo.testapp.domain.entities

/**
 * A generic wrapper class around data request
 */

sealed class DataEntity<T> {
    class ERROR<T>(var error: ErrorEntity) : DataEntity<T>()

    class SUCCESS<T>(var data: T? = null) : DataEntity<T>()

    class LOADING<T> : DataEntity<T>()

}