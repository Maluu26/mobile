package com.example.app5.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.app5.R
import com.example.app5.databinding.ActivityResultsScreenBinding
import com.example.app5.dto.CepDto
import com.example.app5.mainModelView.MainViewModel

class ResultsScreen : AppCompatActivity() {
    private lateinit var bindingInfos: ActivityResultsScreenBinding
    private lateinit var mainVM: MainViewModel;

    private lateinit var cepInfo : CepDto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingInfos= ActivityResultsScreenBinding.inflate(layoutInflater)
        setContentView(bindingInfos.root)
        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        @Suppress("DEPRECATION")
        val cep = intent.getSerializableExtra("EXTRA_CEP_DTO") as? CepDto

        setObservers()
        cep?.let {
            mainVM.setCep(cep)
            mainVM.setCapital(cep.estado)
        }
    }
    private fun setObservers(){
        mainVM.cepInfo().observe(this){
            cep ->
            bindingInfos.cepValue.text = cep.cep
            bindingInfos.lograValue.text = cep.logradouro
            bindingInfos.complementoValue.text = cep.complemento
            bindingInfos.bairroValue.text = cep.bairro
            bindingInfos.cidadeValue.text = cep.localidade
            bindingInfos.estadoValue.text = cep.estado
            bindingInfos.regValue.text = cep.regiao
            bindingInfos.dddValue.text = cep.ddd
            bindingInfos.codigoValue.text = cep.ibge
            bindingInfos.mapa.setImageResource(mainVM.getMapa(cep.uf))
            bindingInfos.imgBandeira.setImageResource(mainVM.getBandeira(cep.uf))
            bindingInfos.estadoReal.text =  cep.estado
            bindingInfos.sigla.text = cep.uf
        }
        mainVM.capitalSelecionado().observe(this){
            capital ->
            bindingInfos.capitalValue.text = capital
        }
    }
}