package com.example.androidmaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmaster.firstApp.FirstAppActivity
import com.example.androidmaster.firstApp.ResultActivity
import com.example.androidmaster.imccalculator.imcCalculatorActivity
import com.example.androidmaster.superhero.SuperHeroListActivity
import com.example.androidmaster.todoapp.TodoActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_menu)

        val btnSaludApp = findViewById<Button>(R.id.btnSaludApp);
        val btnImcApp = findViewById<Button>(R.id.btnIMCApp);
        val btnTODO =  findViewById<Button>(R.id.btnTODO);
        val btnSuperhero = findViewById<Button>(R.id.btnSuperhero)


        btnTODO.setOnClickListener(){
            navigateToTodoApp()

        }

        btnImcApp.setOnClickListener(){
            navigateToImcApp()
        }


        btnSaludApp.setOnClickListener{
            navigateToSaluApp()
        }
        btnSuperhero.setOnClickListener{
            navigateToSuperhero()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    private fun navigateToSuperhero(){
        val intent = Intent(this, SuperHeroListActivity::class.java)
        startActivity(intent)



    }


    private fun navigateToTodoApp(){
        val intent = Intent(this, TodoActivity::class.java)
        startActivity(intent)

    }

    private  fun navigateToSaluApp(){
        val intent = Intent(this, FirstAppActivity::class.java)
        startActivity(intent)

    }

    private  fun navigateToImcApp(){
        val intent = Intent(this, imcCalculatorActivity::class.java)
        startActivity(intent)

    }
}