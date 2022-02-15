package com.example.jokesapp.data.remote

import com.example.jokesapp.core.Constant.BASE_URL
import com.example.jokesapp.core.Constant.BASE_URL_SUBMIT
import com.example.jokesapp.data.remote.dto.JokesDto
import com.example.jokesapp.data.remote.dto.PostJokeSingleDto
import com.example.jokesapp.data.remote.dto.PostJokeTwoPartDto
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class JokesRemoteSourceImpl(
    private val client: HttpClient
) : JokesRemoteSource {

    override suspend fun getJokes(
        category: String,
        queryString: String,
        type: String,
        count: Int,
        blacklistFlags: String,
        isSafeMode: Boolean
    ): JokesDto {


        return client.get {
            url("$BASE_URL/$category?blacklistFlags=$blacklistFlags&contains=${queryString.replace(" ", "%20")}&type=$type&amount=$count&${if (isSafeMode) "safe-mode" else ""}")
            /* parameter("blacklistFlags", blacklistFlags)
             parameter("contains", queryString)
             parameter("type", type)
             parameter("amount", "10${if (isSafeMode)"safe-mode" else ""}")*/
        }
    }

    override suspend fun addJoke(
        jokeSingle: PostJokeSingleDto?,
        jokeTwoPart: PostJokeTwoPartDto?
    ) {

        client.post<String> {
            url(BASE_URL_SUBMIT)
            body = jokeSingle ?: jokeTwoPart!!
            contentType(ContentType.Application.Json)
        }
    }
}