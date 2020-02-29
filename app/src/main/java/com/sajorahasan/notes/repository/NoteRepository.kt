package com.sajorahasan.notes.repository

import androidx.lifecycle.LiveData
import com.sajorahasan.notes.db.dao.NoteDao
import com.sajorahasan.notes.db.entity.Note
import kotlinx.coroutines.CoroutineExceptionHandler
import timber.log.Timber

class NoteRepository(private val noteDao: NoteDao) {
    private val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun deleteAllNotes() {
        noteDao.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }

//    private class InsertNoteAsyncTask(val noteDao: NoteDao) : AsyncTask<Note, Unit, Unit>() {
//        override fun doInBackground(vararg note: Note?) {
//            noteDao.insert(note[0]!!)
//        }
//    }
//
//
//    private class DeleteAllNotesAsyncTask(val noteDao: NoteDao) : AsyncTask<Unit, Unit, Unit>() {
//        override fun doInBackground(vararg p0: Unit?) {
//            noteDao.deleteAllNotes()
//        }
//    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        postError(e.message ?: e.toString())
    }

    private fun postError(message: String) {
        Timber.e("An error happened: $message")
    }
}