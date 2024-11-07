package com.example.androidmaster.todoapp

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.R

class TasksViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    private val  tvTask:TextView = view.findViewById(R.id.tvTasks)
    private val  cbTask:CheckBox = view.findViewById(R.id.cbTask)

    fun render(task: Task){
        tvTask.text = task.name
    }
}