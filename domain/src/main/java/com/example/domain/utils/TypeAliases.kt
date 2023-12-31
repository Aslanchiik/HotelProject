package com.example.domain.utils

import kotlinx.coroutines.flow.Flow

internal typealias RemoteWrapper<T> = Flow<Either<NetworkError, T>>
