package com.example.androidmaster.superhero

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidmaster.R
import com.example.androidmaster.databinding.ActivitySuperHeroListBinding
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class SuperHeroListActivity : AppCompatActivity() {

    private lateinit var  binding:ActivitySuperHeroListBinding
    private lateinit var retrofit: Retrofit
    private  lateinit var adapter:SuperheroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()

    }

    private fun initUI() {
        adapter = SuperheroAdapter()
        binding.rvSuperHero.setHasFixedSize(true)
        binding.rvSuperHero.layoutManager = LinearLayoutManager(this)
        binding.rvSuperHero.adapter = adapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Lanza una coroutine en el hilo IO para la operación de red o base de datos
                CoroutineScope(Dispatchers.IO).launch {
                    // Cambia al hilo principal para mostrar la barra de progreso
                    withContext(Dispatchers.Main) {
                        binding.progressBar.isVisible = true
                    }

                    val response = searchByName(query.orEmpty())

                    // Si hay una respuesta, cambia de nuevo al hilo principal para actualizar la UI
                    withContext(Dispatchers.Main) {
                        binding.progressBar.isVisible = false
                        response?.let {
                            Log.i("response",response.toString())
                            val respuesta : SuperHeroDataResponse?  = response.body()

                            if(respuesta != null){
                                adapter.updateList(respuesta.superheroes)

                            }

                        }
                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }


        })


    }



    private suspend fun searchByName(query: String): Response<SuperHeroDataResponse>? {
        val myResponse :Response<SuperHeroDataResponse> = getRetrofit().create(ApiService::class.java).getSuperheroes(query)
        return if (myResponse.isSuccessful) {
            Log.i("Funciona", "peticion")
            myResponse
        } else {
            Log.i("no funciona", "error")
            null
        }
    }


    private fun getRetrofit(): Retrofit{
        return  Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}