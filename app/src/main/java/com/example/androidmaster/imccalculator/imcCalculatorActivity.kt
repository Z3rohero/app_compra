package com.example.androidmaster.imccalculator

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmaster.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import org.w3c.dom.Text

class imcCalculatorActivity : AppCompatActivity() {

    private var isMaleSelected:Boolean =  false
    private var isFemaleSelected:Boolean = false
    private var currentWeight:Int = 60
    private var currentAge:Int  = 5
    private var currentHeight:Int = 120
    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView
    private lateinit var tvHeight:TextView
    private lateinit var rsHeight:RangeSlider
    private lateinit var btnSubtractWeight:FloatingActionButton
    private lateinit var btnPlusWeight:FloatingActionButton
    private lateinit var btnSubtractAge:FloatingActionButton
    private lateinit var btnPlusAge:FloatingActionButton
    private lateinit var tvWeight:TextView
    private lateinit var tvAge:TextView
    private lateinit var btnCalculate:Button

    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculator)

        initComponents()
        initListeners()
        initUI()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initComponents(){
        viewMale =  findViewById(R.id.viewMale)
        viewFemale =  findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnPlusAge = findViewById(R.id.btnSubtractAge)
        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        tvWeight = findViewById(R.id.tvWeight)
        tvAge =  findViewById(R.id.tvAge)
        btnCalculate = findViewById(R.id.btnCalculate)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initListeners(){
        viewMale.setOnClickListener(){
            setGenderColor()
            changeGender("male")

        }
        viewFemale.setOnClickListener(){
            setGenderColor()
            changeGender("female")

        }
        rsHeight.addOnChangeListener{ _,value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight  = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm"

        }

        btnPlusWeight.setOnClickListener(){
            currentWeight += 1
            setWeigth()

        }
        btnSubtractWeight.setOnClickListener(){
            currentWeight -= 1
            setWeigth()

        }


        btnPlusAge.setOnClickListener(){
            currentAge += 1
            setAge()

        }
        btnSubtractAge.setOnClickListener(){
            currentAge -= 1
            setAge()

        }
        btnCalculate.setOnClickListener(){
          var result =  calculateIMC()

            Log.i("valor final "," est el valor result $result")
            navigateToResult(result)

        }

    }

    private fun navigateToResult(result:Double){
        var intent = Intent(this,ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY,result)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun calculateIMC():Double{
        val df =    DecimalFormat("#.##")
        val imc = currentWeight/(currentHeight.toDouble()/100 * currentHeight.toDouble()/100)

        return  df.format(imc).toDouble()
    }
    private fun  setWeigth(){
        tvWeight.text = currentWeight.toString()
    }

    private fun  setAge(){
        tvAge.text = currentAge.toString()
    }

    private fun changeGender(gender: String) {
        when (gender) {
            "male" -> {
                isMaleSelected = true
                isFemaleSelected = false
            }
            "female" -> {
                isFemaleSelected = true
                isMaleSelected = false
            }
        }
        setGenderColor()
    }
    private  fun setGenderColor(){
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))

    }

    private fun getBackgroundColor(isSelectdComponent:Boolean):Int{
        val colorReferencia= if(isSelectdComponent)
        {
          R.color.background_component_selected
        } else{
           R.color.background_component
        }
        return ContextCompat.getColor(this,colorReferencia)
    }

    private fun initUI(){
        setGenderColor()
        setWeigth()
        setAge()

    }



}