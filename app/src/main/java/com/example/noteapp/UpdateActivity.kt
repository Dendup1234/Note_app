package com.example.noteapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.noteapp.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding : ActivityUpdateBinding
    private lateinit var  db : NoteDatabaseHelper
    private var noteID : Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        db = NoteDatabaseHelper(this)
        noteID = intent.getIntExtra("note_id",-1)
        val note = db.getNoteByID(noteID)
        binding.updateTitleEditText.setText(note.title)
        binding.updateContentEditText.setText(note.content)

        binding.updateButton.setOnClickListener {
            val newTitle = binding.updateTitleEditText.text.toString()
            val newContent = binding.updateContentEditText.text.toString()
            val updatedNote = Note(noteID,newTitle,newContent)
            db.updateNote(updatedNote)
            finish()
            Toast.makeText(this,"Changes saved", Toast.LENGTH_SHORT).show()
        }
    }
}