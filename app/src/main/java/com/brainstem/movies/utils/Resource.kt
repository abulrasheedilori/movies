package com.google.movies.utils

data class Resource<out T>(val status: Status, val data: T?, val exception: Throwable?) {
    operator fun invoke(): T? = this.data

    private val isLoading: Boolean
        get() = this.status == Status.LOADING

    private val isError: Boolean
        get() = this.status == Status.ERROR

    private val isSuccess: Boolean
        get() = this.status == Status.SUCCESS

    val isRefreshing: Boolean
        get() = isLoading && this.data != null

    val isCompleted: Boolean
        get() = isSuccess || isError

    companion object {
        fun <T> success(data: T?): Resource<T> = Resource(Status.SUCCESS, data, null)

        fun <T> error(ex: Throwable, data: T?): Resource<T> = Resource(Status.ERROR, data, ex)

        fun <T> loading(data: T?): Resource<T> = Resource(Status.LOADING, data, null)

        fun <T> initial(data: T? = null): Resource<T> = Resource(Status.INITIAL, data, null)
    }

    enum class Status { SUCCESS, ERROR, LOADING, INITIAL }
}