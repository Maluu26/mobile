package com.mobile.app_pokemon.UI

import android.os.Bundle
import android.view.View
import android.widget.ImageView
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
