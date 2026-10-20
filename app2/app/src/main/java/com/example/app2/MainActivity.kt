package com.example.app2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.app2.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityMainBinding;

    private lateinit var mainVM: MainViewModel;

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
        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)
        observers()
        binding.botaoGuardar.setOnClickListener(this)
        val nome = mainVM.getNomeUsuario().toString()

        if(nome!= ""){
            startActivity(Intent(this, MainActivity2::class.java))
            finish()
        }
    }
    private fun observers(){
        mainVM.nome().observe(this){ novoNome ->
            binding.name.setText(novoNome)
    }
    }
    override fun onClick(view: View) {
        if (view.id == R.id.botaoGuardar){
            val nome = binding.name.text.toString()
            if (mainVM.cadastrarNome(nome)==true){
                startActivity(Intent(this, MainActivity2::class.java))
                finish()
            }else{
                val erro = R.string.erro_nome
                Toast.makeText(applicationContext,erro, Toast.LENGTH_LONG).show()
            }

        }
    }
}
