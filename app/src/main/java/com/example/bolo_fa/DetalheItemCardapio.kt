package com.example.bolo_fa

import Controller.AuthenticationController
import Controller.BoloAdapter
import Controller.BoloController
import Controller.CarrinhoCompraController
import Controller.DoceFestaController
import Model.CarrinhoCompra
import Model.ItemDetalheCardapio
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bolo_fa.databinding.ActivityCardapioListaBinding
import com.example.bolo_fa.databinding.ActivityDetalheItemCardapioBinding
import com.google.firebase.firestore.ListenerRegistration

class DetalheItemCardapio : AppCompatActivity() {

    private lateinit var  binding: ActivityDetalheItemCardapioBinding
    private lateinit var boloAdapter: BoloAdapter
    private var listBolo = mutableListOf<ItemDetalheCardapio>()
    private var listDoce = mutableListOf<ItemDetalheCardapio>()
    private var boloListener: ListenerRegistration? = null
    private var doceListener: ListenerRegistration? = null
    private lateinit var controller: AuthenticationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityDetalheItemCardapioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nome = intent.getStringExtra("nome")
        val categoria = intent.getStringExtra("categoria")

        binding.textViewNomeBolo.text = nome

        binding.sair.setOnClickListener{
            val it = Intent(this, CardapioLista::class.java)
            startActivity(it)
        }

        if(categoria == "bolo"){
            exibirDetalheBolo()
        }else{
            exibirDetalheDoce()
        }

        binding.buttonCarrinho.setOnClickListener {

            controller = AuthenticationController()

            val carrinhoItem = CarrinhoCompra().apply {
                email = controller.usuarioAutenticado()
                quantidade = 1
                produto = intent.getStringExtra("nome")
                preco =  binding.textViewPrecoBolo.text.toString()
            }

            val controllerCarinhoCompra = CarrinhoCompraController()

            controllerCarinhoCompra.adicionarCarrinhoCompra(carrinhoItem){sucesso, erro ->
                if(sucesso){
                    Toast.makeText(this, "Item adicionado ao pedido com sucesso", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, CardapioLista::class.java )
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Erro ao adicionar o item", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun exibirDetalheBolo() {
        val nome = intent.getStringExtra("nome")

        val boloController = BoloController()

        boloListener = boloController.ExibirBoloDetalhe(nome).addSnapshotListener { querySnapshot, error ->
            if (error != null) {
                return@addSnapshotListener
            }

            querySnapshot?.let {

                val document = it.documents.firstOrNull()

                document?.let { document ->

                    binding.textViewDescricaoBolo.text = document.getString("descricao")
                    binding.textViewPrecoBolo.text = document.getString("preco")
                }
            }
        }
    }

    private fun exibirDetalheDoce(){
        val nome = intent.getStringExtra("nome")

        val doceController = DoceFestaController()

        doceListener = doceController.exibirDoceFestaDetalhe(nome).addSnapshotListener{  querySnapshot, error ->
            if(error != null){
                return@addSnapshotListener
            }

            querySnapshot?.let{
                val document = it.documents.firstOrNull()

                document?.let { document ->

                    binding.textViewDescricaoBolo.text = document.getString("descricao")
                    binding.textViewPrecoBolo.text = document.getString("preco")
                }
            }

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        boloListener?.remove()
        doceListener?.remove()
    }
}