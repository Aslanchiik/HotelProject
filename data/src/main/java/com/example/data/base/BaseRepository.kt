package com.example.data.base

import android.util.Log
import com.example.data.BuildConfig
import com.example.data.utils.DataMapper
import com.example.domain.utils.Either
import com.example.domain.utils.NetworkError
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import okhttp3.ResponseBody
import retrofit2.Response

abstract class BaseRepository {

    protected fun <T : DataMapper<S>, S> doNetworkRequestWithMapping(
        request: suspend () -> Response<T>
    ): Flow<Either<NetworkError, S>> = doNetworkRequest(request) { body ->
        Either.Right(body.mapToDomain())
    }

    protected fun <T> doNetworkRequestWithoutMapping(
        request: suspend () -> Response<T>
    ): Flow<Either<NetworkError, T>> = doNetworkRequest(request) { body ->
        Either.Right(body)
    }

    protected fun <T : DataMapper<S>, S> doNetworkRequestForList(
        request: suspend () -> Response<List<T>>
    ): Flow<Either<NetworkError, List<S>>> = doNetworkRequest(request) { body ->
        Either.Right(body.map { it.mapToDomain() })
    }

    private fun <T, S> doNetworkRequest(
        request: suspend () -> Response<T>, successful: (T) -> Either.Right<S>
    ) = flow {
        request().let {
            when {
                it.isSuccessful && it.body() != null -> {
                    emit(successful.invoke(it.body()!!))
                }

                !it.isSuccessful && it.code() == 422 -> {
                    emit(Either.Left(NetworkError.ApiInputs(it.errorBody().toApiError())))
                }

                else -> {
                    emit(Either.Left(NetworkError.Api(it.errorBody().toApiError())))
                }
            }
        }
    }.flowOn(Dispatchers.IO).catch { exception ->
        val message = exception.localizedMessage ?: "Error Occurred!"
        if (BuildConfig.DEBUG) Log.d(this@BaseRepository.javaClass.simpleName, message)
        emit(Either.Left(NetworkError.Unexpected(message)))
    }

    private inline fun <reified T> ResponseBody?.toApiError(): T {
        return Gson().fromJson(
            this?.string(), object : TypeToken<T>() {}.type
        )
    }

    protected inline fun <T : Response<S>, S> T.onSuccess(block: (S) -> Unit): T {
        this.body()?.let(block)
        return this
    }

    protected fun <T : DataMapper<S>, S> doLocalRequestForList(
        request: () -> Flow<List<T>>
    ): Flow<List<S>> = request().map { list -> list.map { data -> data.mapToDomain() } }
}