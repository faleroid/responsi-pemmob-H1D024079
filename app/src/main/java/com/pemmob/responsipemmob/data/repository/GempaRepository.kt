package com.pemmob.responsipemmob.data.repository

import com.pemmob.responsipemmob.data.model.Gempa
import com.pemmob.responsipemmob.data.remote.BmkgApiService
import com.pemmob.responsipemmob.data.remote.RetrofitInstance

class GempaRepository(
    private val api: BmkgApiService = RetrofitInstance.api
) {

    suspend fun getGempaTerkini(): Result<List<Gempa>> = runCatching {
        api.getGempaTerkini()
            .infoGempa
            ?.gempa
            .orEmpty()
    }
}