package network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import network.data.AccessTokenRepository
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

internal fun createNetworkClient(
    loggingInterceptor: HttpLoggingInterceptor
): HttpClient {
    val okHttpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) { json() }
        defaultRequest { url(urlString = Endpoints.BASE_URL) }
        install(DefaultRequest) {
            header(key = HttpHeaders.ContentType, value = ContentType.Application.Json)
            header(key = HttpHeaders.Accept, value = ContentType.Application.Json)
        }
        engine {
            addInterceptor(loggingInterceptor)
        }
        install(Auth) {
            bearer {
                loadTokens { null }
                refreshTokens { null }
            }
        }
    }
    return okHttpClient
}