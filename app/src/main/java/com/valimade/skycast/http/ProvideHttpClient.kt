package com.valimade.skycast.http

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun provideHttpClient(
    nameAPI: NameAPI,
): HttpClient {
    val url = when(nameAPI) {
        NameAPI.WEATHER -> "https://api.tomorrow.io"
        NameAPI.GEOCODING  -> "https://api.geoapify.com"
    }

    return HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
        defaultRequest {
            url (url)
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }
}
