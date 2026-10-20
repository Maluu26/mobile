package com.mobile.app3.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.mobile.app3.MainViewModel.MainViewModel
import com.mobile.app3.R
import com.mobile.app3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainVM: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainVM = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.botao.setOnClickListener(this)
        observers()
    }
    fun observers(){
        mainVM.getPostAtual().observe(this){
            novoPost ->
            if(novoPost!=""){
                binding.post.text = novoPost
            }
            else{
                binding.post.text = R.string.posts.toString()}
        }
    }
    override fun onClick(view: View) {
        if(view.id == R.id.botao){
            val id = binding.idPost.text.toString()
            if(id!=""){
                mainVM.requestPost(id)
            }else{
                Toast.makeText(this, "Digite um ID", Toast.LENGTH_SHORT).show()
            }

        }
    }
}