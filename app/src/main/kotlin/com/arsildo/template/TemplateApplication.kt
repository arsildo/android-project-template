package com.arsildo.template

import android.app.Application
import com.arsildo.template.di.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TemplateApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TemplateApplication)
            modules(applicationModule)
        }
    }
}