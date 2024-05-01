package network.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatchersModule = module {
    single<CoroutineDispatcher>(named(name = "Main")) { Dispatchers.Main }
    single<CoroutineDispatcher>(named(name = "IO")) { Dispatchers.IO }
}
