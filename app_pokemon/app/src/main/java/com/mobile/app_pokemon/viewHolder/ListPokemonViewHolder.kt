package com.mobile.app_pokemon.viewHolder;
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobile.app_pokemon.R
import com.mobile.app_pokemon.databinding.ListPokemonsBinding
import com.mobile.app_pokemon.entity.PokemonEntity;
import com.mobile.app_pokemon.listener.OnPokemonListener;


public class ListPokemonViewHolder(private val binding: ListPokemonsBinding, private val listener:OnPokemonListener): RecyclerView.ViewHolder(binding.root) {

    fun bindVH(poke:PokemonEntity){
        binding.name.text = poke.name
        Glide.with(itemView.context)
            .load(poke.getImageUrl())
            .placeholder(R.drawable.pokeball_pokemon_svgrepo_com)
            .error(R.drawable.diamond_svgrepo_com)
            .into(binding.pokeImg)
        binding.root.setOnClickListener {
            listener.onClick(poke)
        }

    }

}



