package com.example.androidmaster.superhero

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmaster.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperheroViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding  =  ItemSuperheroBinding.bind(view)
    fun bind(SuperheroItemResponse : SuperheroItemResponse){
        binding.tvSuperheroName.text = SuperheroItemResponse.name
        Picasso.get().load(SuperheroItemResponse.image.url).into(binding.ivSuperhero)
    }
}