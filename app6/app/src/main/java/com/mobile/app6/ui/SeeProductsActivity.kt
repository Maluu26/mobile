package com.mobile.app6.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mobile.app6.R
import com.mobile.app6.databinding.ActivitySeeProductsBinding

import com.mobile.app6.viewModel.AddProductView
import com.mobile.app6.viewModel.SeeProductsView

class SeeProductsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var activitySeeProductsBinding: ActivitySeeProductsBinding
    private lateinit var seeProdVM: SeeProductsView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySeeProductsBinding = ActivitySeeProductsBinding.inflate(layoutInflater)
        setContentView(activitySeeProductsBinding.root)
        activitySeeProductsBinding.procura.setOnClickListener(this)
        activitySeeProductsBinding.voltar.setOnClickListener(this)
        seeProdVM = ViewModelProvider(this).get(SeeProductsView::class.java)
        setObserver()
    }
    private fun setObserver(){
        seeProdVM.getProduto().observe(this){ PROD ->
            if(PROD != null && PROD.id != 0 && PROD.nome.isNotBlank()){
                activitySeeProductsBinding.nomeBusca.text = PROD.nome
                activitySeeProductsBinding.valorBusca.text = PROD.preco.toString()
                activitySeeProductsBinding.quantBusca.text = PROD.disponivel.toString()}
        }
        seeProdVM.getIsSaved().observe(this, Observer {
            if (it == 2){
                Toast.makeText(this, "Nenhum produto foi encontrado", Toast.LENGTH_SHORT).show()
                clear()
            } else if (it == 1){
                Toast.makeText(this, "Produto encontrado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Erro no banco.", Toast.LENGTH_SHORT).show()
                clear()

            }
        })
    }

    private fun clear(){
        activitySeeProductsBinding.nomeBusca.text = ""
        activitySeeProductsBinding.valorBusca.text = ""
        activitySeeProductsBinding.quantBusca.text =""
    }

    override fun onClick(view: View) {
        if(view.id == R.id.voltar){
            finish()
        }
        if(view.id == R.id.procura){
            if(activitySeeProductsBinding.productName.text.toString().length>0){

                seeProdVM.getProdutos(activitySeeProductsBinding.productName.text.toString())

            }
        }
    }
}