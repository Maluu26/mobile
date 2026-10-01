package com.example.app2

import android.content.Context
import android.content.Intent
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
class MainActivity2 : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener {

    private lateinit var binding : ActivityMain2Binding;

    private lateinit var sp: SharedPreferences;

    private var opcao: String? = "Belinha"

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
        binding.frases.setOnLongClickListener(this)

        sp = applicationContext.getSharedPreferences("CHAVE_ACESSO", Context.MODE_PRIVATE)

        if(applicationContext.getSharedPreferences("animal", Context.MODE_PRIVATE)!=null){
            opcao = sp.getString("animal", "")
        }
        binding.name2.text = sp.getString("name", "")

        val branquinho = findViewById<ImageView>(R.id.gato)

        val belinha = findViewById<ImageView>(R.id.cachorro)

        val jabuti = findViewById<ImageView>(R.id.jabuti)

        val voltar = findViewById<ImageView>(R.id.botaoVoltar)

        if(opcao == "Belinha"){
            binding.frases.text = curiosidades.getCuriosidadeBelinha()
            belinha.colorFilter  = ColorMatrixColorFilter(matrix)
        }
        else if(opcao == "Branquinho"){
            binding.frases.text = curiosidades.getCuriosidadesBranquinho()
            branquinho.colorFilter  = ColorMatrixColorFilter(matrix)
        }
        else if(opcao == "Juju"){
            binding.frases.text = curiosidades.getCuriosidadesJuju()
            jabuti.colorFilter  = ColorMatrixColorFilter(matrix)
        }

        belinha.setOnClickListener {
            opcao = "Belinha"
            sp.edit().putString("animal", opcao).apply()
            branquinho.colorFilter = null
            jabuti.colorFilter = null
            belinha.colorFilter  = ColorMatrixColorFilter(matrix)
            binding.frases.text = curiosidades.getCuriosidadeBelinha()

        }
        branquinho.setOnClickListener {
            opcao = "Branquinho"
            sp.edit().putString("animal", opcao).apply()
            belinha.colorFilter = null
            jabuti.colorFilter = null
            branquinho.colorFilter  = ColorMatrixColorFilter(matrix)
            binding.frases.text = curiosidades.getCuriosidadesBranquinho()

        }
        jabuti.setOnClickListener {
            opcao = "Juju"
            sp.edit().putString("animal", opcao).apply()
            branquinho.colorFilter = null
            belinha.colorFilter = null
            jabuti.colorFilter  = ColorMatrixColorFilter(matrix)
            binding.frases.text = curiosidades.getCuriosidadesJuju()
        }

        voltar.setOnClickListener {
            sp.edit().putString("name", "").apply()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onClick(view: View) {

        if(view.id == R.id.botaoFrase){
            if(opcao == "Belinha"){
                binding.frases.text = curiosidades.getCuriosidadeBelinha()
            }
            else if(opcao == "Branquinho"){
                binding.frases.text = curiosidades.getCuriosidadesBranquinho()
            }
            else if(opcao == "Juju"){
                binding.frases.text = curiosidades.getCuriosidadesJuju()
            }
        }

        }

    override fun onLongClick(view: View?): Boolean {
        if(view?.id == R.id.frases){
            val remove =binding.frases.text.toString()
            if(opcao == "Belinha"){
                binding.frases.text = curiosidades.getCuriosidadeBelinha()
            }
            else if(opcao == "Branquinho"){
                binding.frases.text = curiosidades.getCuriosidadesBranquinho()
            }
            else if(opcao == "Juju"){
                binding.frases.text = curiosidades.getCuriosidadesJuju()
            }
            curiosidades.removeCuriosidades(remove)
            return true
        }
        return false
    }
}
