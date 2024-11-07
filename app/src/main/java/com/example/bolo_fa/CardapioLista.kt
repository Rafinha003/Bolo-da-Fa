package com.example.bolo_fa

import Controller.BoloController
import Controller.CardapioAdapter
import Model.Cardapio
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bolo_fa.databinding.ActivityCardapioListaBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class CardapioLista : AppCompatActivity() {

    private lateinit var binding: ActivityCardapioListaBinding
    private lateinit var adapter: CardapioAdapter
    private var list = mutableListOf<Cardapio>()
    private var listener: ListenerRegistration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardapioListaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CardapioAdapter(list)
        binding.rvTarefas.layoutManager = LinearLayoutManager(this)
        binding.rvTarefas.adapter = adapter

        setupFirestoreListener()
    }

    private fun setupFirestoreListener() {
        val controllerBolo = BoloController()

        listener = controllerBolo.listarTodosOsBolos().addSnapshotListener{ querySnapshot, error ->
            if (error != null) {
                return@addSnapshotListener
            }

            querySnapshot?.let {
                val listaBolo = it.documents.map {
                    document ->
                    Cardapio(
                        document.getString("nome")
                    )
                }
                adapter.atualizarLista(listaBolo)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        listener?.remove() // Remove o listener quando a activity for destruída para evitar vazamento de memória
    }
}
