package com.example.bolo_fa

import Controller.AuthenticationController
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bolo_fa.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var  binding: ActivityMainBinding
    private lateinit var controller: AuthenticationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCadastrar.setOnClickListener{
            val intent = Intent(this, TelaCadastro::class.java )
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener{
            val email = binding.etEmail.text.toString()
            val senha = binding.etSenha.text.toString()

            controller =  AuthenticationController()

            controller.login(email, senha){sucesso, erro ->
                if(sucesso){
                    Toast.makeText(this, "O usuário autenticado com sucesso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, CardapioLista::class.java )
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Usuário não existe", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.tvEsqueceuSenha.setOnClickListener{
            val intent = Intent(this, EsqueceuSenha::class.java)
            startActivity(intent)
        }
    }
}