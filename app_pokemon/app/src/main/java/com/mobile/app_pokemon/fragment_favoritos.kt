package com.mobile.app_pokemon

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
import com.mobile.app_pokemon.listener.OnFavoritesListener
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
        setupRecyclerView()
        observeViewModel()

    }
    override fun onResume() {
        super.onResume()
        viewModel.getFavoritePokemons()
    }
    private fun setupRecyclerView() {
        adapter = FavoritesAdapter()

        adapter.setListener(object : OnFavoritesListener {
            override fun onClick(p: Pokemon) {
                viewModel.requestPokemon(p.nome)
            }

            override fun onClickFav(p: Pokemon) {
                AlertDialog.Builder(requireContext())
                    .setTitle("Desfavoritando Pokemon")
                    .setMessage("Tem certeza que deseja tirar ${p.nome} dos seus favoritos?")
                    .setPositiveButton("Sim", { dialog, bt ->
                        viewModel.removeFilled(p.nome)

                    })
                    .setNegativeButton("Não", { dialog, bt ->


                    })
                    .create()
                    .show()
            }
        })
        binding.recyclerListPokemons.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerListPokemons.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.message.observe(viewLifecycleOwner) { msg ->
            Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
        }


        viewModel.favList.observe(viewLifecycleOwner) { lista ->
            adapter.updatepokemonList(lista)
        }
        viewModel.pokemonDetails.observe(viewLifecycleOwner) { poke ->
            if (poke == null) return@observe
            val action = fragment_favoritosDirections.actionFragmentFavoritosToTelaPokemon(
                poke.id,
                poke.name,
                poke.types.joinToString(", ") { it.type.name },
                poke.stats.joinToString(", ") { "${it.stat.name}: ${it.baseStat}" },
                poke.sprites.other.officialArtwork.frontDefault
            )
            findNavController().navigate(action)
            viewModel.detalhesAbertos()
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) { msg ->
            Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
        }

    }
}