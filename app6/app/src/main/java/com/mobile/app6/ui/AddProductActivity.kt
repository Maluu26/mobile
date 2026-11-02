package com.mobile.app6.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mobile.app6.R
import com.mobile.app6.data.entity.Produto
import com.mobile.app6.databinding.AddProductBinding
import com.mobile.app6.viewModel.AddProductView

class AddProductActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var AddProductVM: AddProductView;
    private lateinit var addProductBinding: AddProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addProductBinding = AddProductBinding.inflate(layoutInflater)
        setContentView(addProductBinding.root)
        addProductBinding.insereProduto.setOnClickListener(this)
        addProductBinding.voltar.setOnClickListener(this)
        AddProductVM = ViewModelProvider(this).get(AddProductView::class.java)
        setObserver()
    }

    private fun clearEdits(){
        addProductBinding.productName.getText().clear()
        addProductBinding.productValue.getText().clear()
        addProductBinding.productQuanti.getText().clear()

    }

    private fun setObserver(){
        AddProductVM.getIsSaved().observe(this, Observer {
            if (it == 1) {
                Toast.makeText(this, "Salvo", Toast.LENGTH_SHORT).show()
                clearEdits()
            } else if (it == 2) {
                Toast.makeText(this, "Falho 1", Toast.LENGTH_SHORT).show()
            } else if (it == 3) {
                Toast.makeText(this, "Falho 2", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onClick(v: View) {
        if (v.id == R.id.insereProduto) {
            try {
                val p = Produto().apply {
                    this.nome = addProductBinding.productName.text.toString()
                    this.preco = addProductBinding.productValue.text.toString().toFloat()
                    this.disponivel = addProductBinding.productQuanti.text.toString().toInt()

                }
                AddProductVM.saveProduct(p)
                clearEdits()
            }catch (error: NumberFormatException){
                Toast.makeText(this,    "Erro númerico", Toast.LENGTH_SHORT).show()
            }
        }
        if(v.id == R.id.voltar){
            clearEdits()
            finish()
        }

    }
}