package com.example.baru2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.baru2.Note
import kotlinx.android.synthetic.main.not_item.view.*
import kotlinx.android.synthetic.main.not_item.view.*

class NoteAdapter : ListAdapter<Note, NoteAdapter.NoteHolder>(DIFF_CALLBACK) {
    companion object {
        //setting ketika item sama
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.nama == newItem.nama && oldItem.checkin == newItem.checkin && oldItem.checkout == newItem.checkout
                        && oldItem.kamar == newItem.kamar && oldItem.priority == newItem.priority
            }
        }
    }
    //menghubungkan dengan note_item.xml
    private var listener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.not_item, parent, false)
        return NoteHolder(itemView)
    }
    //membuat holder dan deklarasi data agar data dari db bisa di grab dan ditampilkan pada xml
    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote: Note = getItem(position)
        holder.textViewNama.text = currentNote.nama
        holder.textViewPriority.text = currentNote.priority.toString()
        holder.textViewCheckin.text = currentNote.checkin
        holder.textViewCheckout.text = currentNote.checkout
        holder.textViewKamar.text = currentNote.kamar
    }
    fun getNoteAt(position: Int): Note {
        return getItem(position)
    }
    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }
        var textViewNama: TextView = itemView.text_view_nama
        var textViewPriority: TextView = itemView.text_view_priority
        var textViewCheckin: TextView = itemView.text_view_checkin
        var textViewCheckout: TextView = itemView.text_view_checkout
        var textViewKamar: TextView = itemView.text_view_kamar
    }
    interface OnItemClickListener {
        fun onItemClick(note: Note)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}