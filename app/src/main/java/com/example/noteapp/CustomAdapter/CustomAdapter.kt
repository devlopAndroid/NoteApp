package com.example.noteapp.CustomAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.Room.Note
import com.example.noteapp.databinding.CustomLayoutBinding

class CustomAdapter(private val context: Context,
                    val onDeleteClick: OnDeleteClick,
                    val onUpdateClick: OnUpdateClick)
    : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    private var allDataList : List<Note> = listOf()
    class MyViewHolder(item: CustomLayoutBinding) : RecyclerView.ViewHolder(item.root){
        val title = item.noteTitle
        val delete = item.delete
        val time = item.timestamp
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: CustomLayoutBinding = CustomLayoutBinding.
        inflate(LayoutInflater.from(context),parent,false)
        return MyViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val noteType = allDataList[position]
        holder.title.text = noteType.title
        holder.time.text = "Last Update : ${noteType.timestamp}"
        holder.delete.setOnClickListener {
            onDeleteClick.deleteIconClick(noteType)
        }
        holder.itemView.setOnClickListener {
            onUpdateClick.updateIconClick(noteType)
        }
    }

    override fun getItemCount(): Int {
        return allDataList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updatelist(newList : List<Note>){
        allDataList = newList
        notifyDataSetChanged()
    }
}
interface OnDeleteClick{
    fun deleteIconClick(note: Note)
}
interface OnUpdateClick{
    fun updateIconClick(note: Note)
}

