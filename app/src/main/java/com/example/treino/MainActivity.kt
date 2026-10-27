package com.example.treino

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.treino.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.enviar.setOnClickListener(this)
    }
   override fun onClick(view: View){
        if(R.id.enviar==view.id){
            var prim_nome = binding.primNome.text.toString()
            var sec_nome = binding.secNome.text.toString()
            var sobrenome = binding.sobrenome.text.toString()
            if(prim_nome.length<1 || sobrenome.length<1){
                val msg = "Nome incompleto"
                Toast.makeText(applicationContext,msg, Toast.LENGTH_SHORT).show()
            }else{
                val nome_completo = " $prim_nome $sec_nome $sobrenome"
                binding.resultado.text = "${nome_completo}"
                Toast.makeText(applicationContext, "Nome completo concatenado com sucesso", Toast.LENGTH_SHORT).show()
            }
        }
    }
}