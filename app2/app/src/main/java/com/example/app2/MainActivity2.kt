package com.example.app2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.app2.databinding.ActivityMain2Binding
import com.example.app2.databinding.ActivityMainBinding

class MainActivity2 : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMain2Binding;

    private lateinit var sp: SharedPreferences;

    private var curiosidadesBelinha : List<String> = mutableListOf("Belinha tem 13 anos", "Belinha ama queijo (Ela conhece o barulho da queijeira", "Belinha nunca foi mãe")

    private var curiosidadesBranquinho : List<String> = mutableListOf("Branquinho tem 13 olhos", "Branquinho odeia água", "Braquinho ama árvores de natal")

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
    }
    override fun onClick(view: View) {

    }

}