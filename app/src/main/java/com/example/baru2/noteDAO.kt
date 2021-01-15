package com.example.baru2

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    //deklarasi insert untuk mamasukkan data pada db
    @Insert
    fun insert(note: Note)
    //deklarasi update untuk mengubah data pada db
    @Update
    fun update(note: Note)
    //deklarasi delete untuk menghapus data pada db
    @Delete
    fun delete(note: Note)
    //membuat query untuk hapus
    @Query("DELETE FROM note_table")
    fun deleteAllNotes()
    //query untuk mengambil semua data
    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    fun getAllNotes(): LiveData<List<Note>>
}