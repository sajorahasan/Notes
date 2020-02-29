package com.sajorahasan.notes

import android.app.Application
import com.sajorahasan.notes.di.dbModule
import com.sajorahasan.notes.di.repositoryModule
import com.sajorahasan.notes.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CoreApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CoreApp)
            modules(listOf(uiModule, repositoryModule, dbModule))
        }
    }
}