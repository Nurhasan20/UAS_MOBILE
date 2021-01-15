package com.example.baru2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.baru2.Note
import com.example.baru2.NoteRepository

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private var repository: NoteRepository = NoteRepository(application)
    private var allNotes: LiveData<List<Note>> = repository.getAllNotes()
    //deklarasi insert untuk mamasukkan data pada db
    fun insert(note: Note) {
        repository.insert(note)
    }
    //deklarasi update untuk mengubah data pada db
    fun update(note: Note) {
        repository.update(note)
    }
    //deklarasi delete untuk menghapus data pada db
    fun delete(note: Note) {
        repository.delete(note)
    }
    //deklarasi deleteall untuk menghapus semua data pada db
    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }
    //deklarasi untuk menampilkan data
    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }
}