package com.example.app2

import android.content.Intent
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.app2.databinding.ActivityMain2Binding
class MainActivity2 : AppCompatActivity(), View.OnClickListener, View.OnLongClickListener {

    private lateinit var binding : ActivityMain2Binding;

    val matrix = ColorMatrix().apply { setSaturation(0f) }

    private lateinit var mainVM : MainViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.botaoFrase.setOnClickListener(this)
        binding.frases.setOnLongClickListener(this)
        binding.gato.setOnClickListener(this)
        binding.cachorro.setOnClickListener(this)
        binding.jabuti.setOnClickListener(this)
        binding.botaoVoltar.setOnClickListener(this)

        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)
        observers()
        val nome = mainVM.getNomeUsuario()
    }
    private fun observers(){
        mainVM.fraseAnimais().observe(this){ novaFrase ->
            binding.frases.text = novaFrase

        }
        mainVM.animalSelecionado.observe(this){ novoAnimal ->
            atualizaFiltros(novoAnimal)

        }
        mainVM.nome().observe(this){ novoNome ->
            binding.name2.text = novoNome
        }

    }
    private fun atualizaFiltros(animalSelecionado: String){
        binding.gato.colorFilter = null
        binding.cachorro.colorFilter = null
        binding.jabuti.colorFilter = null

        when(animalSelecionado){
            "Belinha" -> binding.cachorro.colorFilter = ColorMatrixColorFilter(matrix)
            "Branquinho" -> binding.gato.colorFilter = ColorMatrixColorFilter(matrix)
            "Juju" -> binding.jabuti.colorFilter = ColorMatrixColorFilter(matrix)
        }
    }
    override fun onClick(view: View) {
        if(view.id == R.id.botaoFrase){
           mainVM.buscarNovaFrase()
        }
        else if(view.id == R.id.gato){
            mainVM.selecionarAnimal("Branquinho")
        }
        else if(view.id == R.id.cachorro){
            mainVM.selecionarAnimal("Belinha")
        }
        else if(view.id == R.id.jabuti){
            mainVM.selecionarAnimal("Juju")
        }
        else if(view.id == R.id.botaoVoltar){
            mainVM.limparUsuario()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onLongClick(view: View?): Boolean {
        if(view?.id == R.id.frases){
            mainVM.removerFraseAtual()
            return true
        }
        return false
    }
}
