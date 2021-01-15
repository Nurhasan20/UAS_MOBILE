package com.example.baru2

import androidx.room.Entity
import androidx.room.PrimaryKey
//deklarasi data
@Entity(tableName = "note_table")
data class Note(
    var nama: String?,
    val checkin: String?,
    val checkout: String?,
    var kamar: String?,
    var priority: Int ) {

    @PrimaryKey(autoGenerate = true) var id: Int = 0
}