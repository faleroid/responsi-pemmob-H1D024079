package com.pemmob.responsipemmob.data.remote

import com.pemmob.responsipemmob.data.model.GempaResponse
import retrofit2.http.GET

interface BmkgApiService {

    @GET("DataMKG/TEWS/gempaterkini.json")
    suspend fun getGempaTerkini(): GempaResponse
}