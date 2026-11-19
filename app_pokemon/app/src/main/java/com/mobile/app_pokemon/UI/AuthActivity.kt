package com.mobile.app_pokemon.UI

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.mobile.app_pokemon.R
import com.mobile.app_pokemon.databinding.ActivityAuthBinding
import com.mobile.app_pokemon.databinding.ActivityMainBinding

class AuthActivity : AppCompatActivity(){
    lateinit var binding: ActivityAuthBinding
    private val auth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            findViewById<View>(R.id.main).setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }

    }


}