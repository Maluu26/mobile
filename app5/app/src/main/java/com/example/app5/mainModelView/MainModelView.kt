package com.example.app5.mainModelView

import com.example.app5.R
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.application
import com.example.app5.client.ClienteRetrofit
import com.example.app5.dto.CepDto
import com.example.app5.entity.CepEntity
import com.example.app5.service.CepService
import retrofit2.Call
import retrofit2.Response

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val cepSelecionado = MutableLiveData<String>()
    fun cepSelecionado(): LiveData<String> = cepSelecionado

    private val capitalSelecionado = MutableLiveData<String>()
    fun capitalSelecionado(): LiveData<String> = capitalSelecionado
    private val logradouro = MutableLiveData<String>()
    fun logradouro(): LiveData<String> = logradouro

    private val bandeiraSelecionada = MutableLiveData<String>()
    fun bandeiraSelecionada(): LiveData<String> = bandeiraSelecionada
    private val cepInfo = MutableLiveData<CepDto>()
    fun cepInfo(): LiveData<CepDto> = cepInfo


    private val erro = MutableLiveData<String>()
    fun erro(): LiveData<String> = erro


    fun setCapital(uf: String)
    {
       capitalSelecionado.value = when(uf){
            "Acre" -> "Rio Branco"
            "Amapá" -> "Macapá"
           "Amazonas" -> "Manaus"
           "Rondônia" -> "Porto Velho"
           "Roraima" -> "Boa Vista"
           "Pará" -> "Belém"
           "Tocantins" -> "Palmas"
           "Bahia" -> "Salvador"
           "Sergipe" -> "Aracaju"
           "Maranhão" -> "São Luís"
           "Ceará" -> "Fortaleza"
           "Pernambuco" -> "Recife"
           "Piauí" -> "Teresina"
           "Paraíba" -> "João Pessoa"
           "Rio Grande do Norte" -> "Natal"
           "Alagoas" -> "Maceió"
           "Rio de Janeiro" ->"Rio de Janeiro"
           "São Paulo" ->"São Paulo"
           "Minas Gerais" ->"Belo Horizonte"
           "Espírito Santo" ->"Vitória"
           "Santa Catarina" ->"Florianópolis"
           "Paraná" ->"Curitiba"
           "Rio Grande do Sul" ->"Porto Alegre"
           "Mato Grosso" ->"Cuiabá"
           "Mato Grosso do Sul" ->"Campo Grande"
           "Goiás" ->"Goiânia"
           "Distrito Federal" -> "Brasília"
            else -> "..."
        }

    }
    fun getBandeira(ufOuEstado: String): Int {
        return when (ufOuEstado.trim()) {
            "AC", "Acre" -> R.drawable.bandeira_acre
            "AL", "Alagoas" -> R.drawable.bandeira_alagoas
            "AP", "Amapá" -> R.drawable.bandeira_amapa
            "AM", "Amazonas" -> R.drawable.bandeira_amazonas
            "BA", "Bahia" -> R.drawable.bandeira_bahia
            "CE", "Ceará" -> R.drawable.bandeira_ceara
            "ES", "Espírito Santo" -> R.drawable.bandeira_espirito_santo
            "GO", "Goiás" -> R.drawable.bandeira_goias
            "MA", "Maranhão" -> R.drawable.bandeira_maranhao
            "MT", "Mato Grosso" -> R.drawable.bandeira_de_mato_grosso
            "MS", "Mato Grosso do Sul" -> R.drawable.bandeira_mato_grosso_do_sul
            "MG", "Minas Gerais" -> R.drawable.bandeira_minas_gerais
            "PA", "Pará" -> R.drawable.bandeira_para
            "PB", "Paraíba" -> R.drawable.bandeira_da_paraiba
            "PR", "Paraná" -> R.drawable.bandeira_parana
            "PE", "Pernambuco" -> R.drawable.bandeira_pernambuco
            "PI", "Piauí" -> R.drawable.bandeira_piaui
            "RJ", "Rio de Janeiro" -> R.drawable.bandeira_rio_de_janeiro
            "RN", "Rio Grande do Norte" -> R.drawable.bandeira_rio_grande_do_norte
            "RS", "Rio Grande do Sul" -> R.drawable.bandeira_rio_grande_do_sul
            "RO", "Rondônia" -> R.drawable.bandeira_rondonia
            "RR", "Roraima" -> R.drawable.bandeira_roraima
            "SC", "Santa Catarina" -> R.drawable.bandeira_santa_catarina
            "SP", "São Paulo" -> R.drawable.bandeira_sao_paulo
            "SE", "Sergipe" -> R.drawable.bandeira_sergipe
            "TO", "Tocantins" -> R.drawable.bandeira_tocantins
            "DF", "Distrito Federal" -> R.drawable.bandeira_distrito_federal__brasil_
            else -> R.drawable.pin
        }
    }
    fun getMapa(ufOuEstado: String): Int {
        return when (ufOuEstado.trim()) {
            "AC", "Acre" -> R.drawable.mapa_ac
            "AL", "Alagoas" -> R.drawable.mapa_al
            "AP", "Amapá" -> R.drawable.mapa_ap
            "AM", "Amazonas" -> R.drawable.mapa_am
            "BA", "Bahia" -> R.drawable.mapa_ba
            "CE", "Ceará" -> R.drawable.mapa_ce
            "ES", "Espírito Santo" -> R.drawable.mapa_es
            "GO", "Goiás" -> R.drawable.mapa_go
            "MA", "Maranhão" -> R.drawable.mapa_ma
            "MT", "Mato Grosso" -> R.drawable.mapa_mt
            "MS", "Mato Grosso do Sul" -> R.drawable.mapa_ms
            "MG", "Minas Gerais" -> R.drawable.mapa_mg
            "PA", "Pará" -> R.drawable.mapa_pa
            "PB", "Paraíba" -> R.drawable.mapa_pb
            "PR", "Paraná" -> R.drawable.mapa_pr
            "PE", "Pernambuco" -> R.drawable.mapa_pe
            "PI", "Piauí" -> R.drawable.mapa_pi
            "RJ", "Rio de Janeiro" -> R.drawable.mapa_rj
            "RN", "Rio Grande do Norte" -> R.drawable.mapa_rn
            "RS", "Rio Grande do Sul" -> R.drawable.mapa_rs
            "RO", "Rondônia" -> R.drawable.mapa_ro
            "RR", "Roraima" -> R.drawable.mapa_rr
            "SC", "Santa Catarina" -> R.drawable.mapa_sc
            "SP", "São Paulo" -> R.drawable.mapa_sp
            "SE", "Sergipe" -> R.drawable.mapa_se
            "TO", "Tocantins" -> R.drawable.mapa_to
            "DF", "Distrito Federal" -> R.drawable.mapa_df
            else -> R.drawable.pin
        }
    }
    fun setCep(cepDto: CepDto){
        if(cepDto != null){
            cepInfo.value = cepDto
        }
    }
    fun getInformacoesCep(cep: String) {
        if (cep.length != 8) {
            Toast.makeText(
                application,
                "Tamanho Inváilido de Cep. Tente novamente!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            requestCep(cep)
        }

    }
    fun requestCep(cep: String) {
        val apiCep = ClienteRetrofit.createService(CepService::class.java)
        val cepInfos: Call<CepEntity> = apiCep.getCepData(cep)
        cepInfos.enqueue(object : retrofit2.Callback<CepEntity>{
            override fun onResponse(
                call: Call<CepEntity>,
                response: Response<CepEntity>
            ) {

                if (response.isSuccessful && response.body()!!.cep != "") {
                    cepInfo.value = CepDto(response.body()!!.cep, response.body()!!.logradouro ?: "-",
                        response.body()!!.complemento, response.body()!!.unidade ?: "-", response.body()!!.bairro,
                        response.body()!!.localidade ?: "-", response.body()!!.uf, response.body()!!.estado,
                        response.body()!!.regiao ?: "-", response.body()!!.ibge, response.body()!!.gia,
                        response.body()!!.ddd, response.body()!!.siafi)
                    Log.e("info cep", "cep aí ${cepInfo.value.toString()}")
                } else {
                    // Erros HTTP (ex: 404 Not Found, 500 Internal Server Error)
                    erro.value = "CEP Inexistente"
                    val erroHttp = "Erro HTTP: ${response.code()}"
                    Log.e("RetrofitDebug", "Erro na resposta: ${response.errorBody()?.string()}")
                   erro.value = erroHttp
                }
            }

            override fun onFailure(call: Call<CepEntity>, t: Throwable) {
                erro.value = "Falha na requisição da API"
                Log.e("RetrofitDebug", "Falha crítica: ${t.message}", t)
            }
        })
    }


}
