package com.mobile.app_pokemon.viewHolder

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.app_pokemon.R
import com.mobile.app_pokemon.data.entity.Pokemon
import com.mobile.app_pokemon.databinding.ListPokemonsBinding
import com.mobile.app_pokemon.databinding.ListaFavoritosBinding
import com.mobile.app_pokemon.entity.PokemonEntity;
import com.mobile.app_pokemon.listener.OnPokemonListener;

class FavoritesViewHolder(private val binding: ListaFavoritosBinding, private val listener: OnPokemonListener) : RecyclerView.ViewHolder(binding.root){

    fun bindVH(poke: Pokemon){
        binding.name.text = poke.nome
        val color = ContextCompat.getColor(binding.root.context, R.color.red)
        binding.isFav.setColorFilter(color)
        Glide.with(itemView.context)
            .load(poke.url)
            .placeholder(R.drawable.pokeball_pokemon_svgrepo_com)
            .error(R.drawable.diamond_svgrepo_com)
            .into(binding.pokeImg)
        binding.root.setOnClickListener {
            listener.onClickFav(poke)
        }

    }
}