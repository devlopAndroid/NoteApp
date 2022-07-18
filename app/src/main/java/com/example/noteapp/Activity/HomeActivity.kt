package com.example.noteapp.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.CustomAdapter.CustomAdapter
import com.example.noteapp.CustomAdapter.OnDeleteClick
import com.example.noteapp.CustomAdapter.OnUpdateClick
import com.example.noteapp.Room.Note
import com.example.noteapp.Room.NoteApplication
import com.example.noteapp.Viewmodel.NoteViewModel
import com.example.noteapp.Viewmodel.NoteViewModelFactory
import com.example.noteapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), OnDeleteClick, OnUpdateClick {
    lateinit var Hbinding: ActivityHomeBinding
    private val myviewModel : NoteViewModel by viewModels{
        NoteViewModelFactory((application as NoteApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Hbinding = ActivityHomeBinding.inflate(layoutInflater)
        val view = Hbinding.root
        setContentView(view)

        Hbinding.floatingActionButton.setOnClickListener {
            val intent = Intent(this@HomeActivity,AddDetail::class.java)
            startActivity(intent)
            this.finish()
        }
       Hbinding.recycler.layoutManager = LinearLayoutManager(this)
       val myAdapter = CustomAdapter(this@HomeActivity,this,this)
       Hbinding.recycler.adapter = myAdapter

       myviewModel.allNote.observe(this, Observer { list->
        list?.let{
            myAdapter.updatelist(list)
        }
       })
    }

    override fun deleteIconClick(note: Note) {
      myviewModel.delete(note)
      Toast.makeText(this,"${note.title} Deleted",Toast.LENGTH_SHORT).show()
    }

    override fun updateIconClick(note: Note) {
        val intent = Intent(this,AddDetail::class.java)
        intent.putExtra("HomeType","Edit")
        intent.putExtra("title",note.title)
        intent.putExtra("discription",note.discription)
        intent.putExtra("noteId",note.id)
        startActivity(intent)
        this.finish()
    }
}