package com.example.noteapp.Activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.noteapp.R
import com.example.noteapp.Room.Note
import com.example.noteapp.Room.NoteApplication
import com.example.noteapp.Viewmodel.NoteViewModel
import com.example.noteapp.Viewmodel.NoteViewModelFactory
import com.example.noteapp.databinding.ActivityAddDetailBinding
import java.text.SimpleDateFormat
import java.util.*

class AddDetail : AppCompatActivity() {
    lateinit var Dbinding: ActivityAddDetailBinding
    private val myviewmodel : NoteViewModel by viewModels {
        NoteViewModelFactory((application as NoteApplication).repository)
    }
    var noteId = -1
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Dbinding = ActivityAddDetailBinding.inflate(layoutInflater)
        val view = Dbinding.root
        setContentView(view)
        setupActionbar()

        val homeType = intent.getStringExtra("HomeType")
        if (homeType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("title")
            val noteDiscription = intent.getStringExtra("discription")
            noteId = intent.getIntExtra("noteId", -1)
            Dbinding.button.text = "UPDATE"
            Dbinding.editTitle.setText(noteTitle)
            Dbinding.editDiscription.setText(noteDiscription)
        } else {
            Dbinding.button.text = "SAVE"
        }
        Dbinding.button.setOnClickListener {
            val title = Dbinding.editTitle.text.toString()
            val discription = Dbinding.editDiscription.text.toString()
            if (homeType.equals("Edit")) {
                if (title.isNotEmpty() && discription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    val updateNote = Note(title, discription, currentDate)
                    updateNote.id = noteId
                    myviewmodel.update(updateNote)
                    Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"Data is empty",Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                if (title.isNotEmpty() && discription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    val insertNote = Note(title,discription,currentDate)
                    myviewmodel.insert(insertNote)
                    Toast.makeText(this,"Note Added",Toast.LENGTH_SHORT).show()
                }
            }
            startActivity(Intent(applicationContext,HomeActivity::class.java))
            this.finish()
        }
    }
    private fun setupActionbar() {
       setSupportActionBar(Dbinding.toolbar)
       supportActionBar?.setDisplayHomeAsUpEnabled(true)
       supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
       Dbinding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}