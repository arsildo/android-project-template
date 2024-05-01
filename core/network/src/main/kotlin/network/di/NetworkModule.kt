package network.di

import network.createNetworkClient
import network.data.AccessTokenRepository
import network.interceptors.addLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {
    singleOf(::addLoggingInterceptor)
    singleOf(::createNetworkClient)
}