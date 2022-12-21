package com.emm.domain.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <DataSourceType, DomainModelType> buildFlowWithResultState(
    crossinline fetchData: suspend () -> ResultState<DataSourceType>,
    crossinline mapToDomainModel: (DataSourceType) -> DomainModelType
): Flow<ResultState<DomainModelType>> = flow {
    emit(fetchData())
}.map { result: ResultState<DataSourceType> ->
    result.mapper { value: DataSourceType -> mapToDomainModel(value) }
}

