package com.mobile.app_pokemon.UI

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.mobile.app_pokemon.BuscaFragment
import com.mobile.app_pokemon.R
import com.mobile.app_pokemon.all_pokemons
import com.mobile.app_pokemon.databinding.ActivityMainBinding
import com.mobile.app_pokemon.fragment_favoritos

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            navegarPara(BuscaFragment())
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val headerView = findViewById<View>(R.id.header)
            headerView.setPadding(
                headerView.paddingLeft,
                systemBars.top,
                headerView.paddingRight,
                headerView.paddingBottom
            )
            findViewById<View>(R.id.main).setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }
        binding.diamond.setImageResource(R.drawable.diamond_verde_com)
        binding.diamond.setOnClickListener(this)
        binding.square.setOnClickListener(this)
        binding.circle.setOnClickListener(this)
    }
    fun setYellow(){
        binding.diamond.setImageResource(R.drawable.diamond_svgrepo_com)
        binding.circle.setImageResource(R.drawable.circle_svgrepo_com)
        binding.square.setImageResource(R.drawable.square_svgrepo_com)
    }
    override fun onClick(view: View) {
        if(R.id.diamond == view.id){
            setYellow()
            binding.diamond.setImageResource(R.drawable.diamond_verde_com)
            navegarPara(BuscaFragment())
        }
        if(R.id.circle == view.id){

            setYellow()
            binding.circle.setImageResource(R.drawable.circle_verde_com)
            navegarPara(fragment_favoritos())
        }
        if(R.id.square == view.id){
            setYellow()
            binding.square.setImageResource(R.drawable.square_verde)
            navegarPara(all_pokemons())
        }

    }
    fun navegarPara(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }
    }

}