package com.mobile.app_pokemon

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobile.app_pokemon.UI.tela_pokemon
import com.mobile.app_pokemon.adapter.FavoritesAdapter
import com.mobile.app_pokemon.adapter.PokemonAdapter
import com.mobile.app_pokemon.data.entity.Pokemon
import com.mobile.app_pokemon.databinding.FragmentAllPokemonsBinding
import com.mobile.app_pokemon.databinding.FragmentFavoritosBinding
import com.mobile.app_pokemon.entity.PokemonEntity
import com.mobile.app_pokemon.listener.OnPokemonListener
import com.mobile.app_pokemon.viewModel.MainActivityViewModel
import kotlin.getValue

class fragment_favoritos : Fragment() {

    private lateinit var _binding: FragmentFavoritosBinding
    private val binding get() = _binding

    private lateinit var adapter: FavoritesAdapter

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavoritePokemons()
        setupRecyclerView()
        observeViewModel()

    }
    private fun setupRecyclerView() {
        adapter = FavoritesAdapter()

        adapter.setListener(object : OnPokemonListener {
            override fun onClick(p: PokemonEntity) {
                TODO("Not yet implemented")
            }

            override fun onClickFav(p: Pokemon) {
                viewModel.getFavoritePokemons()
            }
        })
        binding.recyclerListPokemons.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerListPokemons.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.favList.observe(viewLifecycleOwner) { lista ->
            adapter.updatepokemonList(lista)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { msg ->
            Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
        }

    }
}