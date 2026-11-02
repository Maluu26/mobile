package com.mobile.app6.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.app6.R
import com.mobile.app6.data.entity.Produto
import com.mobile.app6.databinding.ActivityListingBinding
import com.mobile.app6.ui.adapter.ListProdutoAdapter
import com.mobile.app6.ui.listener.OnProductListener
import com.mobile.app6.viewModel.AddProductView
import com.mobile.app6.viewModel.ListProductView


class listing : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityListingBinding
    private lateinit var listingVM: ListProductView
    private val adapter =  ListProdutoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listingVM = ViewModelProvider(this).get(ListProductView::class.java)
        binding.voltar.setOnClickListener(this)
        binding.recyclerListProducts.layoutManager = LinearLayoutManager(this)
        binding.recyclerListProducts.adapter = adapter

        supportActionBar?.hide()
        val listener = object : OnProductListener {
            override fun onClick(p: Produto) {
                Toast.makeText(applicationContext, p.nome, Toast.LENGTH_SHORT).show()
            }
        }
        adapter.setListener(listener)

        listingVM.getTodosProdutos()

        setObserver()

    }
    private fun setObserver(){
        listingVM.getProduto().observe(this, Observer {
            adapter.updateProdList(it)
        })
        listingVM.getIsSaved().observe(this, Observer {
            if (it == 2){
                Toast.makeText(this, "Nenhum produto foi encontrado", Toast.LENGTH_SHORT).show()

            } else if (it == 1){
                Toast.makeText(this, "Produto encontrado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Erro no banco.", Toast.LENGTH_SHORT).show()

            }
        })
    }

    override fun onClick(v: View) {
        if(v.id == R.id.voltar){
            finish()
        }
    }
}