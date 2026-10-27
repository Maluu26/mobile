package com.example.app5.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.app5.R
import com.example.app5.databinding.ActivityCepBinding
import com.example.app5.dto.CepDto
import com.example.app5.mainModelView.MainViewModel


class CEP : AppCompatActivity(), View.OnClickListener{

    private lateinit var binding: ActivityCepBinding;
    private lateinit var mainVM: MainViewModel;
    private lateinit var cepInfo : CepDto


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCepBinding.inflate(layoutInflater)
        setContentView(binding.root)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)
        observers()
        binding.botCep.setOnClickListener(this)
    }
    private fun observers(){
        mainVM.cepInfo().observe(this){
            novasInfosCep ->
            if (novasInfosCep != null){
                cepInfo = novasInfosCep
                intent = Intent(this, ResultsScreen::class.java).apply {
                    putExtra("EXTRA_CEP_DTO", cepInfo)
                }
                startActivity(intent)
                }
            }

        mainVM.erro().observe(this) { mensagemErro ->
            if (!mensagemErro.isNullOrEmpty()) {
                Toast.makeText(this, mensagemErro, Toast.LENGTH_SHORT).show()
            }
        }

        }

    override fun onClick(view: View) {
        if(view.id == R.id.botCep){
            mainVM.getInformacoesCep(binding.digCep.text.toString())
        }
    }
}