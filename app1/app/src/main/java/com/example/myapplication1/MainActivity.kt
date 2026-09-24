package com.example.myapplication1

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var binding : ActivityMainBinding;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.botaoImposto.setOnClickListener(this)



        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }
        override fun onClick(view: View) {
            if (view.id == R.id.botao_imposto){
                val salario = binding.salario.text.toString().toFloatOrNull() ?: 0f
                val gastos= binding.gastos.text.toString().toFloatOrNull() ?: 0f
                val dependentes= binding.dependentes.text.toString().toFloatOrNull() ?: 0f
                var  imposto = 0f
                if (salario>5000){
                    imposto = salario - gastos - (189.59F * dependentes)
                    imposto = imposto * 0.275F
                }
                binding.inputImposto.text = "${imposto}"
                Toast.makeText(applicationContext,
                    "Imposto calculado com sucesso",
                    Toast.LENGTH_SHORT).show()
            }
        }
}