package com.sajorahasan.notes.di

import com.sajorahasan.notes.adapter.NoteAdapter
import com.sajorahasan.notes.db.NoteDatabase
import com.sajorahasan.notes.repository.NoteRepository
import com.sajorahasan.notes.viewmodel.NoteViewModal
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dbModule = module {
    single { NoteDatabase.getInstance(context = get()) }
    factory { get<NoteDatabase>().noteDao() }
}

val repositoryModule = module {
    single { NoteRepository(get()) }
}

val uiModule = module {
    factory { NoteAdapter() }
    viewModel { NoteViewModal(get()) }
}