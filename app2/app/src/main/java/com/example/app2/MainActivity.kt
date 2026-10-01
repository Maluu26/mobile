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
import com.example.app2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding : ActivityMainBinding;
    private lateinit var sp: SharedPreferences;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.botaoGuardar.setOnClickListener(this)
        sp = applicationContext.getSharedPreferences("CHAVE_ACESSO", Context.MODE_PRIVATE)

        if(sp.getString("name", "")!= ""){
            startActivity(Intent(this, MainActivity2::class.java))
            finish()
        }
    }
    override fun onClick(view: View) {
        if (view.id == R.id.botaoGuardar){
            val nome = binding.name.text.toString()
            if (binding.name.text != null && nome.length>=3){
                sp.edit().putString("name", binding.name.text.toString()).apply()
                startActivity(Intent(this, MainActivity2::class.java))
                finish()

            }else{
                val erro = R.string.erro_nome
                Toast.makeText(applicationContext,erro, Toast.LENGTH_LONG).show()
            }

        }
    }

}