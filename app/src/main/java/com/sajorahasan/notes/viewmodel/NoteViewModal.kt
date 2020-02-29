package com.sajorahasan.notes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sajorahasan.notes.db.entity.Note
import com.sajorahasan.notes.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModal(private var repository: NoteRepository) : ViewModel() {

    private var allNotes: LiveData<List<Note>> = repository.getAllNotes()

    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }

    fun deleteAllNotes() = viewModelScope.launch {
        repository.deleteAllNotes()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }
}