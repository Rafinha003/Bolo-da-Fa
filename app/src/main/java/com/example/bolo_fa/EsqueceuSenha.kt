package com.example.bolo_fa

import Controller.AuthenticationController
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bolo_fa.databinding.ActivityEsqueceuSenhaBinding
import com.example.bolo_fa.databinding.ActivityTelaCadastroBinding

class EsqueceuSenha : AppCompatActivity() {

    private lateinit var binding: ActivityEsqueceuSenhaBinding
    private lateinit var controller: AuthenticationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityEsqueceuSenhaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEnviar.setOnClickListener {
            val email = binding.txtEmail.text.toString()

            controller = AuthenticationController()

            controller.esqueceuSenha(email){sucesso, erro ->
                if(sucesso){
                    Toast.makeText(this, "Um e-mail de redefinição de senha foi enviado para o seu endereço de e-mail.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java )
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Falha ao enviar e-mail de redefinição de senha. Verifique se o endereço de e-mail é válido.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}