package network.interceptors

import okhttp3.logging.HttpLoggingInterceptor

/**
 * `addLoggingInterceptor` is a function that creates and configures an `HttpLoggingInterceptor` instance.
 * It sets the logging level to `BODY`, which means that the request and response data (headers and body) will be logged.
 *
 * @return The configured `HttpLoggingInterceptor` instance.
 */
internal fun addLoggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return loggingInterceptor
}