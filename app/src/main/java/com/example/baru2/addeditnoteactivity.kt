package com.example.baru2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_note.*
//menghubungkan dengan db
class AddEditNoteActivity : AppCompatActivity() {
    companion object { const val EXTRA_ID = "com.piusanggoro.notesapp.EXTRA_ID"
        const val EXTRA_NAMA = "com.piusanggoro.notesapp.EXTRA_NAMA"
        const val EXTRA_CHECKIN = "com.piusanggoro.notesapp.EXTRA_CHECKIN"
        const val EXTRA_CHECKOUT = "com.piusanggoro.notesapp.EXTRA_CHECKOUT"
        const val EXTRA_KAMAR = "com.piusanggoro.notesapp.EXTRA_KAMAR"
        const val EXTRA_PRIORITAS = "com.piusanggoro.notesapp.EXTRA_PRIORITAS"
    }
    //menghubungkan dengan activity_add_note.xml
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        number_picker_priority.minValue = 1
        number_picker_priority.maxValue = 10
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_check_black_24dp)
//membuat atau edit catatan
        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit Reservasi Kamar"
            edit_text_nama.setText(intent.getStringExtra(EXTRA_NAMA))
            edit_text_checkin.setText(intent.getStringExtra(EXTRA_CHECKIN))
            edit_text_checkout.setText(intent.getStringExtra(EXTRA_CHECKOUT))
            edit_text_kamar.setText(intent.getStringExtra(EXTRA_KAMAR))
            number_picker_priority.value = intent.getIntExtra(EXTRA_PRIORITAS, 1)
        } else {
            title = "Buar Reservasi Kamar"
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }
    //menyimpan note
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            R.id.save_note -> {
                saveNote()
                true
            }
            else -> super.onOptionsItemSelected(item!!)
        }
    }
    //setting ketika catatan kosong
    private fun saveNote() {
        if (edit_text_nama.text.toString().trim().isBlank() || edit_text_checkin.text.toString().trim().isBlank()) {
            Toast.makeText(this, "Catatan kosong!", Toast.LENGTH_SHORT).show()
            return
        }
//menyimpan data ke db
        val data = Intent().apply {
            putExtra(EXTRA_NAMA, edit_text_nama.text.toString())
            putExtra(EXTRA_CHECKIN, edit_text_checkin.text.toString())
            putExtra(EXTRA_CHECKOUT, edit_text_checkout.text.toString())
            putExtra(EXTRA_KAMAR, edit_text_kamar.text.toString())
            putExtra(EXTRA_PRIORITAS, number_picker_priority.value)
            if (intent.getIntExtra(EXTRA_ID, -1) != -1) {
                putExtra(EXTRA_ID, intent.getIntExtra(EXTRA_ID, -1))
            }
        }
        setResult(Activity.RESULT_OK, data)
        finish()
    }
}