package com.mobile.app6.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.app6.data.entity.Produto
import com.mobile.app6.databinding.LayoutProductLineBinding
import com.mobile.app6.ui.listener.OnProductListener
import com.mobile.app6.ui.viewHolder.ListProductViewHolder

class ListProdutoAdapter : RecyclerView.Adapter<ListProductViewHolder>() {
    private lateinit var listener: OnProductListener;
    private var prodList: List<Produto> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListProductViewHolder {
        val item = LayoutProductLineBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,false)
        return ListProductViewHolder(item,listener)
    }

    override fun onBindViewHolder(holder: ListProductViewHolder, position: Int) {
        holder.bindVH(prodList[position])
    }

    override fun getItemCount(): Int {
        return prodList.count()
    }

    fun updateProdList(list: List<Produto>) {
        prodList = list
        notifyDataSetChanged()
    }
    fun setListener(productListener: OnProductListener) {
        listener = productListener
    }
}
