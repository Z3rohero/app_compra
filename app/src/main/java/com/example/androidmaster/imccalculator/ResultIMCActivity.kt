package com.example.androidmaster.imccalculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmaster.R
import com.example.androidmaster.imccalculator.imcCalculatorActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_imcactivity)
        val result:Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
         initComponents()
        initUI(result)

    }


    private fun  initComponents(){
        tvIMC = findViewById(R.id.tvIMC)
        tvDescription = findViewById(R.id.tvDescription)
        tvResult = findViewById(R.id.tvResult)
    }

    private fun initUI(result:Double){
        tvIMC.text = result.toString()
        when(result){
            //Bajo Peso
            in 0.00..18.50 ->{

                tvResult.text = getString(R.string.title_bajo_peso)
                tvDescription.text = getString(R.string.description_bajo_peso)
            }
            //Peso Normal
            in 18.51..24.99 ->{

                tvResult.text = getString(R.string.title_peso_normal)
                tvDescription.text = getString(R.string.description_bajo_peso)

            }
            //Sobre peso
            in 25.00..29.99 ->{

                tvResult.text = getString(R.string.title_sobrepeso)
                tvDescription.text = getString(R.string.description_sobrepeso)

            }
            //Obesidad
            in 30.00..99.00 ->{

                tvResult.text = getString(R.string.title_obesidad)
                tvDescription.text = getString(R.string.description_obesidad)
            }
            else -> {
                tvIMC.text= getString(R.string.error)
                tvResult.text=getString(R.string.error)
                tvDescription.text=getString(R.string.error)
            }

        }

    }

}