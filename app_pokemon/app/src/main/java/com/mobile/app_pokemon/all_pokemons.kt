package com.mobile.app_pokemon

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.mobile.app_pokemon.UI.tela_pokemon
import com.mobile.app_pokemon.adapter.PokemonAdapter
import com.mobile.app_pokemon.databinding.FragmentAllPokemonsBinding
import com.mobile.app_pokemon.entity.PokemonEntity
import com.mobile.app_pokemon.listener.OnPokemonListener
import com.mobile.app_pokemon.viewModel.MainActivityViewModel

class all_pokemons : Fragment() {

    private var _binding: FragmentAllPokemonsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var adapter: PokemonAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllPokemonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.view.setOnClickListener { viewModel.requestPokemonList(30) }
        setupRecyclerView()
        observeViewModel()
        if (viewModel.pokemonList.value.isNullOrEmpty()) {
            viewModel.requestPokemonList(30)
        }
    }

    private fun setupRecyclerView() {
        adapter = PokemonAdapter()
        adapter.setListener(object : OnPokemonListener {
            override fun onClick(p: PokemonEntity) {
                viewModel.requestPokemon(p.name)
            }
        })

        binding.recyclerListPokemons.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.recyclerListPokemons.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.pokemonList.observe(viewLifecycleOwner) { lista ->
            adapter.updatepokemonList(lista)
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) { msg ->
            Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
        }
        viewModel.pokemonDetails.observe(viewLifecycleOwner) { poke ->
            if (poke == null) return@observe
            val intent = Intent(requireContext(), tela_pokemon::class.java).apply {
                putExtra("Name", poke.name)
                putExtra("Id", poke.id)
                putExtra("Types", poke.types.joinToString(", ") { it.type.name })
                putExtra(
                    "Stats",
                    poke.stats.joinToString(", ") { "${it.stat.name}: ${it.baseStat}" })
                putExtra("ImageUrl", poke.sprites.other.officialArtwork.frontDefault)
            }
            startActivity(intent)
            viewModel.detalhesAbertos()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}