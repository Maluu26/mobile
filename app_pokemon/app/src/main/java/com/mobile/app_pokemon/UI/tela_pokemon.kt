package com.mobile.app_pokemon.UI

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.mobile.app_pokemon.R
import com.mobile.app_pokemon.databinding.ActivityTelaPokemonBinding
import com.mobile.app_pokemon.viewModel.PokemonViewModel

class tela_pokemon : AppCompatActivity(), View.OnClickListener{
    private lateinit var binding: ActivityTelaPokemonBinding;

    private lateinit var viewModel: PokemonViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityTelaPokemonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =  ViewModelProvider(this).get(PokemonViewModel::class.java)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.backArrow.setOnClickListener(this)
        binding.isFav.setOnClickListener(this)
        val nome = intent.getStringExtra("Name").orEmpty()
        val imageUrl = intent.getStringExtra("ImageUrl")
        val id = intent.getLongExtra("Id", -1L)

        setObservers()
        viewModel.adjustFav(id)

        findViewById<TextView>(R.id.nome_poke).text = nome.replaceFirstChar { it.uppercase() }

        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.pokeball_pokemon_svgrepo_com)
            .error(R.drawable.circle_svgrepo_com)
            .into(findViewById<ImageView>(R.id.pokemon))

        mostrarTipos(intent.getStringExtra("Types").orEmpty())
        mostrarStats(intent.getStringExtra("Stats").orEmpty())
    }

    private fun mostrarTipos(texto: String) {
        val tipos = texto.split(", ").filter { it.isNotBlank() }
        val etiquetas = listOf(binding.tipo1, binding.tipo2)
        etiquetas.forEachIndexed { i, etiqueta ->
            val tipo = tipos.getOrNull(i)
            if (tipo == null) {
                etiqueta.visibility = View.GONE
            } else {
                val (nomePt, cor) = TIPOS[tipo] ?: (tipo to "#777777")
                etiqueta.text = nomePt
                etiqueta.setBackgroundColor(Color.parseColor(cor))
                etiqueta.visibility = View.VISIBLE
            }
        }
    }

    private fun mostrarStats(texto: String) {
        val stats = texto.split(", ").associate { parte ->
            val (nome, valor) = parte.split(": ").let { it.getOrElse(0) { "" } to it.getOrElse(1) { "0" } }
            nome to (valor.toIntOrNull() ?: 0)
        }
        mostrarStat(binding.valorHp, binding.barraHp, stats["hp"])
        mostrarStat(binding.valorAtaque, binding.barraAtaque, stats["attack"])
        mostrarStat(binding.valorDefesa, binding.barraDefesa, stats["defense"])
        mostrarStat(binding.valorAtqEs, binding.barraAtqEs, stats["special-attack"])
        mostrarStat(binding.valorDefEs, binding.barraDefEs, stats["special-defense"])
        mostrarStat(binding.valorVelocidade, binding.barraVelocidade, stats["speed"])
    }

    private fun mostrarStat(valor: TextView, barra: ProgressBar, numero: Int?) {
        val n = numero ?: 0
        valor.text = n.toString()
        barra.progress = n   // a barra vai de 0 a 255 (android:max no layout)
    }

    companion object {
        private val TIPOS = mapOf(
            "normal" to ("Normal" to "#A8A77A"), "fire" to ("Fogo" to "#EE8130"),
            "water" to ("Água" to "#6390F0"), "electric" to ("Elétrico" to "#F7D02C"),
            "grass" to ("Planta" to "#7AC74C"), "ice" to ("Gelo" to "#96D9D6"),
            "fighting" to ("Lutador" to "#C22E28"), "poison" to ("Venenoso" to "#A33EA1"),
            "ground" to ("Terrestre" to "#E2BF65"), "flying" to ("Voador" to "#A98FF3"),
            "psychic" to ("Psíquico" to "#F95587"), "bug" to ("Inseto" to "#A6B91A"),
            "rock" to ("Pedra" to "#B6A136"), "ghost" to ("Fantasma" to "#735797"),
            "dragon" to ("Dragão" to "#6F35FC"), "dark" to ("Sombrio" to "#705746"),
            "steel" to ("Aço" to "#B7B7CE"), "fairy" to ("Fada" to "#D685AD")
        )
    }
    fun setObservers(){
        viewModel.isFav.observe(this) { ehFavorito ->
            if (ehFavorito) {
                binding.isFav.setImageResource(R.drawable.filled_heart)
            } else {
                binding.isFav.setImageResource(R.drawable.heart_svgrepo_com)
            }
        }
    }
    override fun onClick(v: View) {
        val nome = intent.getStringExtra("Name").orEmpty()
        val imageUrl = intent.getStringExtra("ImageUrl").orEmpty()
        val id = intent.getLongExtra("Id", -1L)
        if(v.id == R.id.back_arrow){finish()}
        if(v.id== R.id.is_fav){
            viewModel.alternateFilled(id, nome, imageUrl)
        }
    }
}
