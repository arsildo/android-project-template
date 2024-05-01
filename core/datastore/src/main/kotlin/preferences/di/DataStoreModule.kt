package preferences.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import preferences.createDataStore

val dataStoreModule = module {
    singleOf(::createDataStore)
}