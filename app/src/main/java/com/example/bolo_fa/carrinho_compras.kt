package com.example.bolo_fa

import Controller.AuthenticationController
import Controller.CarrinhoCompraController
import Controller.ResumoCardapioAdapter
import Model.CarrinhoCompra
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bolo_fa.databinding.ActivityCarrinhoComprasBinding
import com.google.firebase.firestore.ListenerRegistration

class carrinho_compras : AppCompatActivity() {
    private lateinit var  binding: ActivityCarrinhoComprasBinding
    private lateinit var controller: AuthenticationController
    private lateinit var adapter: ResumoCardapioAdapter
    private  var list = mutableListOf<CarrinhoCompra>()
    private var listener: ListenerRegistration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityCarrinhoComprasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controller = AuthenticationController()

        adapter = ResumoCardapioAdapter(list)
        binding.itemPedido.layoutManager = LinearLayoutManager(this)
        binding.itemPedido.adapter = adapter

         binding.sair.setOnClickListener{
             val intent = Intent(this, CardapioLista::class.java )
             startActivity(intent)
         }

        iniciarListener()
    }

   private fun iniciarListener(){
       val emailAutenticado: String? = controller.usuarioAutenticado()

       if(emailAutenticado != null ){

          val controllerCarrinhoCompra = CarrinhoCompraController()

           listener = controllerCarrinhoCompra.listarPorEmailUsuario(emailAutenticado).addSnapshotListener{ querySnapshot, error ->
               if(error != null){
                   return@addSnapshotListener
               }
               querySnapshot?.let {
                   val carrinhoCompra = it.documents.map { document ->
                       CarrinhoCompra(
                           document.getString("email"),
                           document.getString("produto"),
                           document.getString("preco"),
                           document.getLong("quantidade")
                       )
                   }
                   adapter.atualizarCarrinho(carrinhoCompra)
               }
           }
       }
   }

    override fun onDestroy() {
        super.onDestroy()
        listener?.remove()
    }
   }

