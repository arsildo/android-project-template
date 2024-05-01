package network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode

/**
 * Sealed class representing the result of an API call.
 *
 * It has three subclasses: Success, Error, and Exception.
 */
sealed class ApiResult<T : Any> {
    /**
     * Represents a successful API call.
     * @property data The data returned from the API call.
     */
    class Success<T : Any>(val data: T) : ApiResult<T>()

    /**
     * Represents an API call that returned an error.
     * @property code The HTTP status code returned from the API call.
     * @property message The error message returned from the API call.
     */
    class Error<T : Any>(val code: Int, val message: String?) : ApiResult<T>()

    /**
     * Represents an API call that threw an exception.
     * @property e The exception thrown during the API call.
     */
    class Exception<T : Any>(val e: Throwable) : ApiResult<T>()
}

/**
 * Handles a POST request to the specified URL and returns the result as an instance of ApiResult.
 * @param urlString The URL to which the POST request is made.
 * @param block A lambda function for configuring the HttpRequestBuilder.
 * @return An instance of ApiResult representing the result of the API call.
 */
suspend inline fun <reified T : Any> HttpClient.handlePostRequest(
    urlString: String,
    block: HttpRequestBuilder.() -> Unit = {}
): ApiResult<T> {
    return try {
        val response = this.post(urlString = urlString, block = block)
        if (response.status == HttpStatusCode.OK) {
            val body = response.body<T>()
            ApiResult.Success(body)
        } else {
            ApiResult.Error(code = response.status.value, message = response.status.description)
        }
    } catch (e: Throwable) {
        ApiResult.Exception(e)
    }
}

/**
 * Handles a GET request to the specified URL and returns the result as an instance of ApiResult.
 * @param urlString The URL to which the GET request is made.
 * @param block A lambda function for configuring the HttpRequestBuilder.
 * @return An instance of ApiResult representing the result of the API call.
 */
suspend inline fun <reified T : Any> HttpClient.handleRequest(
    urlString: String,
    method: HttpMethod,
    block: HttpRequestBuilder.() -> Unit = {}
): ApiResult<T> {
    return try {
        val response = when (method) {
            HttpMethod.Post -> this.post(urlString = urlString, block = block)
            HttpMethod.Put -> this.put(urlString = urlString, block = block)
            else -> this.get(urlString = urlString, block = block)
        }
        if (response.status == HttpStatusCode.OK) {
            val body = response.body<T>()
            ApiResult.Success(body)
        } else {
            ApiResult.Error(code = response.status.value, message = response.status.description)
        }
    } catch (e: Throwable) {
        ApiResult.Exception(e)
    }
}