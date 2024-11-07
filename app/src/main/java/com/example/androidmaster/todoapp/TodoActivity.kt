package com.example.androidmaster.todoapp

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.androidmaster.R
import com.example.androidmaster.todoapp.TaskCategory.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoActivity : AppCompatActivity() {

    private val categories = listOf(
        Business,
        Personal,
        Other

    )

    private val tasks = mutableListOf(
        Task("Prueba", Business),
        Task("Prueba", Personal) ,
        Task("Prueba", Other)


    )
    private lateinit var rvCategory: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var  rvTasks:RecyclerView
    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var  fabAddTask:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_todo)
        initComponent()
        initUI()
        initListeners()
    }

    private fun initListeners(){
        fabAddTask.setOnClickListener(){
            showDialog()
        }
    }
    private fun showDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)
        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        val etTask:EditText = dialog.findViewById(R.id.etTask)
        val rgCategory: RadioGroup =  dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener{
           val currentTask = etTask.text.toString()
             if(currentTask.isNotEmpty()){
                 val selectedId = rgCategory.checkedRadioButtonId
                 val selectedRadioButton = rgCategory.findViewById<RadioButton>(selectedId)
                 val currentCategory:TaskCategory = when(selectedRadioButton.text){
                     getString(R.string.todo_dialog_category_business)->Business
                     getString(R.string.todo_dialog_category_personal) ->Personal
                     else -> Other
                 }
                 tasks.add(Task(etTask.text.toString() , currentCategory))
                 updateTasks()
                 dialog.hide()

             }
        }

        dialog.show()




    }

    private fun updateTasks(){
        tasksAdapter.notifyDataSetChanged()

    }

    private fun initComponent(){
        rvCategory =  findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
        fabAddTask = findViewById(R.id.fabAddTask)

    }

    private fun  initUI(){
        categoriesAdapter = CategoriesAdapter(categories)
        rvCategory.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rvCategory.adapter = categoriesAdapter

        tasksAdapter = TasksAdapter(tasks)
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = tasksAdapter
    }
}