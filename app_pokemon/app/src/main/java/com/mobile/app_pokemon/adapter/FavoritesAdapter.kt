package com.mobile.app_pokemon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.app_pokemon.data.entity.Pokemon
import com.mobile.app_pokemon.databinding.ListaFavoritosBinding
import com.mobile.app_pokemon.listener.OnPokemonListener
import com.mobile.app_pokemon.viewHolder.FavoritesViewHolder


class FavoritesAdapter : RecyclerView.Adapter<FavoritesViewHolder>(){
    private lateinit var listener: OnPokemonListener;
    private var pokemonList: List<Pokemon> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val item = ListaFavoritosBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return FavoritesViewHolder(item, listener)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bindVH(pokemonList[position])
    }

    override fun getItemCount(): Int {
        return pokemonList.count()
    }

    fun updatepokemonList(list: List<Pokemon>) {
        pokemonList = list
        notifyDataSetChanged()
    }
    fun setListener(pokemonListener: OnPokemonListener) {
        listener = pokemonListener
    }
}