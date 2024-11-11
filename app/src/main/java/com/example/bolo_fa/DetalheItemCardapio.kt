package com.example.bolo_fa

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bolo_fa.databinding.ActivityDetalheItemCardapioBinding

class DetalheItemCardapio : AppCompatActivity() {

    private lateinit var  binding: ActivityDetalheItemCardapioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityDetalheItemCardapioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nome = intent.getStringExtra("nome")

        binding.textViewNomeBolo.text = nome

        binding.sair.setOnClickListener{
            val it = Intent(this, CardapioLista::class.java)
            startActivity(it)
        }
    }
}