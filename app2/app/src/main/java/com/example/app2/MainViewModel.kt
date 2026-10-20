package com.example.app2
import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.app2.clients.ClientService
import com.example.app2.entities.curiosidadeCachorro
import com.example.app2.entities.curiosidadeGatos
import com.example.app2.objects.curiosidades
import com.example.app2.service.curiosidadesCachorroService
import com.example.app2.service.curiosidadesGatosService
import retrofit2.Call
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val sp = application.getSharedPreferences("CHAVE_ACESSO", Context.MODE_PRIVATE)

    private val _nome = MutableLiveData<String>()
    fun nome(): LiveData<String> = _nome
    private val _fraseAtual = MutableLiveData<String>()
    fun fraseAnimais(): LiveData<String> = _fraseAtual

    private val _animalSelecionado = MutableLiveData<String>()
    val animalSelecionado: LiveData<String> = _animalSelecionado

    init {
        val animalSalvo = sp.getString("animal", "Belinha") ?: "Belinha"
        selecionarAnimal(animalSalvo)
        _nome.value = getNomeUsuario()
    }
    fun cadastrarNome(novoNome: String): Boolean{
        if (novoNome != null && novoNome.length>=3){
            sp.edit().putString("name", novoNome).apply()
            return true
        }
        return false
    }
    fun selecionarAnimal(animal: String) {
        _animalSelecionado.value = animal
        sp.edit().putString("animal", animal).apply()
        buscarNovaFrase()
    }

    fun buscarNovaFrase() {
        val novaFrase = when (_animalSelecionado.value) {
            ("Belinha") -> requestPostBelinha()
            ("Branquinho") -> requestPostBranquinho()
            ("Juju") -> curiosidades.getCuriosidadesJuju()
            else -> "..."
        }
    }

    fun requestPostBranquinho(){
        val  apiCuriosidadeGatos = ClientService.createCatService(curiosidadesGatosService::class.java)
        val curiosidadeGatos: Call<curiosidadeGatos> = apiCuriosidadeGatos.getCatFact()
        curiosidadeGatos.enqueue(object: retrofit2.Callback<curiosidadeGatos> {
            override fun onResponse(
                call: Call<curiosidadeGatos>,
                response: Response<curiosidadeGatos>
            ) {
                if(response.isSuccessful){
                    _fraseAtual.value = response.body()?.fact}
                else {
                    // Erros HTTP (ex: 404 Not Found, 500 Internal Server Error)
                    val erroHttp = "Erro HTTP: ${response.code()}"
                    Log.e("RetrofitDebug", "Erro na resposta: ${response.errorBody()?.string()}")
                    _fraseAtual.value = erroHttp
                }
            }

            override fun onFailure(call: Call<curiosidadeGatos>, t: Throwable) {
               _fraseAtual.value = "Falha na requisição da API"
                Log.e("RetrofitDebug", "Falha crítica: ${t.message}", t)
            }
        })
    }
    fun requestPostBelinha(){
        val  apicuriosidadeCachorros = ClientService.createDogService(
            curiosidadesCachorroService::class.java)
        val curiosidadeCachorro: Call<curiosidadeCachorro> = apicuriosidadeCachorros.getDogFact()
        curiosidadeCachorro.enqueue(object: retrofit2.Callback<curiosidadeCachorro> {
            override fun onResponse(
                call: Call<curiosidadeCachorro>,
                response: Response<curiosidadeCachorro>
            ) {
                if(response.isSuccessful){

                    _fraseAtual.value = response.body()?.facts[0]}
                else {
                    // Erros HTTP (ex: 404 Not Found, 500 Internal Server Error)
                    val erroHttp = "Erro HTTP: ${response.code()}"
                    Log.e("RetrofitDebug", "Erro na resposta: ${response.errorBody()?.string()}")
                    _fraseAtual.value = erroHttp
                }
            }

            override fun onFailure(call: Call<curiosidadeCachorro>, t: Throwable) {
                _fraseAtual.value = "Falha na requisição da API"
                Log.e("RetrofitDebug", "Falha crítica: ${t.message}", t)
            }
        })
    }
    fun removerFraseAtual(){
        _fraseAtual.value?.let { curiosidades.removeCuriosidades(it) }
        buscarNovaFrase()
    }

    fun getNomeUsuario(): String{
        return sp.getString("name", "") ?: ""

    }

    fun limparUsuario(){
        sp.edit().putString("name", "").apply()
        _nome.value = ""
    }
}
