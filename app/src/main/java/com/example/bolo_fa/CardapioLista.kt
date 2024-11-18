package com.example.bolo_fa

import Controller.BoloController
import Controller.BoloAdapter
import Controller.DoceFestaAdapter
import Controller.DoceFestaController
import Model.Cardapio
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bolo_fa.databinding.ActivityCardapioListaBinding
import com.google.firebase.firestore.ListenerRegistration

class CardapioLista : AppCompatActivity() {

    private lateinit var binding: ActivityCardapioListaBinding
    private lateinit var adapter: BoloAdapter
    private lateinit var adapterDoce: DoceFestaAdapter
    private var list = mutableListOf<Cardapio>()
    private var listDoce = mutableListOf<Cardapio>()
    private var boloListener: ListenerRegistration? = null
    private var doceListener: ListenerRegistration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardapioListaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = BoloAdapter(list)
        adapterDoce = DoceFestaAdapter(listDoce)

        binding.rvTarefas.layoutManager = LinearLayoutManager(this)
        binding.rvTarefas.adapter = adapter

        binding.doceDeFesta.layoutManager = LinearLayoutManager(this)
        binding.doceDeFesta.adapter = adapterDoce

        binding.sair.setOnClickListener{
            val it = Intent(this, MainActivity::class.java)
            startActivity(it)
        }

        binding.carrinhoCompra.setOnClickListener{
            val it = Intent(this, carrinho_compras::class.java)
            startActivity(it)
        }

        carregarTodosOsBolos()
        carregarTodosOsDocesFesta()

    }

    private fun carregarTodosOsBolos() {
        val controllerBolo = BoloController()

        boloListener = controllerBolo.listarTodosOsBolos().addSnapshotListener { querySnapshot, error ->
            if (error != null) {
                return@addSnapshotListener
            }

            querySnapshot?.let {
                val listaBolo = it.documents.map { document ->
                    Cardapio(
                        document.getString("nome")
                    )
                }
                adapter.atualizarListaBolo(listaBolo)
            }
        }
    }

    private fun carregarTodosOsDocesFesta() {
        val controllerDoce = DoceFestaController()

        doceListener = controllerDoce.listarTodosOsDocesFesta().addSnapshotListener { querySnapshot, error ->
            if (error != null) {
                return@addSnapshotListener
            }

            querySnapshot?.let {
                val listaDoce = it.documents.map { document ->
                    Cardapio(
                        document.getString("nome")
                    )
                }
                adapterDoce.atualizarListaDoce(listaDoce)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        boloListener?.remove()
        doceListener?.remove()
    }
}
