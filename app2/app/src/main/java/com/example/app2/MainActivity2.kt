package com.example.app2

import android.content.Context
import android.content.SharedPreferences
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app2.databinding.ActivityMain2Binding
import kotlin.random.Random
import com.example.app2.objects.curiosidades
class MainActivity2 : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMain2Binding;

    private lateinit var sp: SharedPreferences;

    private var opcao = 1

    val matrix = ColorMatrix().apply { setSaturation(0f) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.botaoFrase.setOnClickListener(this)
        sp = applicationContext.getSharedPreferences("CHAVE_ACESSO", Context.MODE_PRIVATE)
        binding.name2.text = sp.getString("name", "")
        binding.frases.text = curiosidades.getCuriosidadeBelinha()

        val branquinho = findViewById<ImageView>(R.id.gato)

        val belinha = findViewById<ImageView>(R.id.cachorro)
        belinha.setOnClickListener {
            branquinho.colorFilter = null
            belinha.colorFilter  = ColorMatrixColorFilter(matrix)
            binding.frases.text = curiosidades.getCuriosidadeBelinha()

        }
        branquinho.setOnClickListener {
            opcao = 0
            belinha.colorFilter = null
            branquinho.colorFilter  = ColorMatrixColorFilter(matrix)
            binding.frases.text = curiosidades.getCuriosidadesBranquinho()

        }
    }

    override fun onClick(view: View) {

        if(view.id == R.id.botaoFrase){
            if(opcao == 1){
                binding.frases.text = curiosidades.getCuriosidadeBelinha()
            }
            else{
                binding.frases.text = curiosidades.getCuriosidadesBranquinho()
            }
        }
    }



}