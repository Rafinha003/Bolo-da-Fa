package com.example.bolo_fa

import Controller.AuthenticationController
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bolo_fa.databinding.ActivityCarrinhoComprasBinding

class carrinho_compras : AppCompatActivity() {
    private lateinit var  binding: ActivityCarrinhoComprasBinding
    private lateinit var controller: AuthenticationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityCarrinhoComprasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controller = AuthenticationController()
        binding.textNomeUsuario.text = controller.usuarioAutenticado()
    }
}