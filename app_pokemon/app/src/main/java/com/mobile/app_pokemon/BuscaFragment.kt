package com.mobile.app_pokemon

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.mobile.app_pokemon.UI.MainActivity
import com.mobile.app_pokemon.UI.tela_pokemon
import com.mobile.app_pokemon.databinding.FragmentAllPokemonsBinding
import com.mobile.app_pokemon.databinding.FragmentBuscaBinding
import com.mobile.app_pokemon.viewModel.MainActivityViewModel
import kotlin.getValue

class BuscaFragment : Fragment() {
    private var _binding: FragmentBuscaBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBuscaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        binding.buscaBotao.setOnClickListener {
            viewModel.requestPokemon(binding.pokeBusca.text.toString())
        }
    }
    private fun observeViewModel() {
        viewModel.pokemonDetails.observe(viewLifecycleOwner) { poke ->
            if (poke == null) return@observe
            val intent = Intent(requireContext(), tela_pokemon::class.java).apply {
                putExtra("Name", poke.name)
                putExtra("Id", poke.id)
                putExtra("Types", poke.types.joinToString(", ") { it.type.name })
                putExtra("Stats", poke.stats.joinToString(", ") { "${it.stat.name}: ${it.baseStat}" })
                putExtra("ImageUrl", poke.sprites.other.officialArtwork.frontDefault)
            }
            startActivity(intent)
            viewModel.detalhesAbertos()
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) { msg ->
            Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}