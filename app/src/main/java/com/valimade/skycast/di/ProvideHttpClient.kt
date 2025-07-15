package com.valimade.skycast.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named

val nameWeather = named("WEATHER")
val nameGeocoding = named("GEOCODING")

fun provideHttpClient(
    nameClient: StringQualifier,
): HttpClient {
    val url = when(nameClient) {
        nameWeather -> "https://api.tomorrow.io"
        nameGeocoding -> "https://api.geoapify.com"
        else -> ""
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
