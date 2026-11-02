package com.mobile.app6.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mobile.app6.R
import com.mobile.app6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener{
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.addCircle.setOnClickListener(this)
        binding.search.setOnClickListener(this)
        binding.list.setOnClickListener (this)

    }
    override fun onClick(view: View?) {
       if(view?.id == R.id.addCircle){
           startActivity(Intent(this, AddProductActivity::class.java))
       }
        else if(view?.id == R.id.search){
        startActivity(Intent(this, SeeProductsActivity::class.java))
        }
       else if(view?.id == R.id.list){
           startActivity(Intent(this, listing::class.java))
       }
    }
}

