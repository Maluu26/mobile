package com.mobile.app_pokemon.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mobile.app_pokemon.databinding.ListPokemonsBinding
import com.mobile.app_pokemon.entity.PokemonEntity
import com.mobile.app_pokemon.listener.OnPokemonListener
import com.mobile.app_pokemon.viewHolder.ListPokemonViewHolder


class PokemonAdapter : RecyclerView.Adapter<ListPokemonViewHolder>() {
    
    private lateinit var listener: OnPokemonListener;
    private var pokemonList: List<PokemonEntity> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPokemonViewHolder {
        val item = ListPokemonsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return ListPokemonViewHolder(item,listener)
    }

    override fun onBindViewHolder(holder: ListPokemonViewHolder, position: Int) {
        holder.bindVH(pokemonList[position])
    }

    override fun getItemCount(): Int {
        return pokemonList.count()
    }

    fun updatepokemonList(list: List<PokemonEntity>) {
        pokemonList = list
        notifyDataSetChanged()
    }
    fun setListener(pokemonListener: OnPokemonListener) {
        listener = pokemonListener
    }
}
