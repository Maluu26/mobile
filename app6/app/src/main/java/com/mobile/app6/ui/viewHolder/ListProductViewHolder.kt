package com.mobile.app6.ui.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.mobile.app6.data.entity.Produto
import com.mobile.app6.databinding.LayoutProductLineBinding
import com.mobile.app6.ui.listener.OnProductListener

class ListProductViewHolder(private val binding: LayoutProductLineBinding, private val listener: OnProductListener) : RecyclerView.ViewHolder(binding.root) {
    fun bindVH(prod: Produto){
        binding.buscaNome.text = prod.nome
        binding.buscaValor.text = prod.preco.toString()
        binding.buscaQuant.text = prod.disponivel.toString()

        binding.textProdName.setOnClickListener({
            listener.onClick(prod)
        })
    }

}
