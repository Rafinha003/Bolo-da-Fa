package com.example.bolo_fa

import Controller.AuthenticationController
import Controller.NomeDataCadastroController
import Model.NomeDataCadastro
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bolo_fa.databinding.ActivityMainBinding
import com.example.bolo_fa.databinding.ActivityTelaCadastroBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TelaCadastro : AppCompatActivity() {

    private lateinit var  binding: ActivityTelaCadastroBinding
    private lateinit var controller: AuthenticationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastro)
        this.binding = ActivityTelaCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java )
            startActivity(intent)
        }

        binding.btnCadastrarUsuario.setOnClickListener{
            val nomeCadastrado = binding.etNomeCadastro.text.toString()
            val email = binding.etEmailCadastro.text.toString()
            val senha = binding.etSenhaCadastro.text.toString()
            val confirmarSenha = binding.etConfirmarSenha.text.toString()

            if(nomeCadastrado.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()){
                Toast.makeText(this, "Existem campos que ainda não foram preenchido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener;
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(this, "E-mail inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(senha != confirmarSenha){
                Toast.makeText(this, "Os valores nos campos Nova Senha e Confirmar Senha não correspondem. Insira a senha desejada em ambos os campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener;
            }

            val nomeCadastro = NomeDataCadastro().apply {
                nome =  binding.etNomeCadastro.text.toString()
                data = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now())
            }

            val nomeCadastroController = NomeDataCadastroController()
            nomeCadastroController.salvarNomeDataCadastro(nomeCadastro){sucesso, erro ->
                if(sucesso){
                    Toast.makeText(this, "Nome gravado com sucesso", Toast.LENGTH_SHORT)
                }else{
                    Toast.makeText(this, "Nome gravado com erro", Toast.LENGTH_SHORT)
                }
            }

            controller = AuthenticationController()

            controller.criarUsuario(email, senha){sucesso, erro ->
                if(sucesso){
                    Toast.makeText(this, "usuário criado com suecesso", Toast.LENGTH_SHORT).show()
                    finish()
                }else{
                    Toast.makeText(this, "falha ao criar o usuário", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}