package com.convector.david_000.convector_valute.utils

import retrofit2.Response
import timber.log.Timber

fun <DTO, VO> Response<DTO>.handle(block: (DTO) -> VO) =
    body().let { response ->
        if (response == null || !this.isSuccessful) {
            Timber.e(response.toString())
            throw RuntimeException(response.toString())
        }
        block(response)
    }

fun <DTO> Response<DTO>.handleNoData() =
    body().let { response ->
        if (response == null) {
            throw RuntimeException(response.toString())
        }
    }

//inline fun <DTO, VO, reified Mapper : ViewObjectMapper<VO, DTO>> Response<ApiResponse<DTO>>.map(mapper: Mapper) =
//    handle(mapper::toViewObject)
