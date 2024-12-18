package Controller

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class DoceFestaController {

    private val db = FirebaseFirestore.getInstance()

    fun listarTodosOsDocesFesta(): Query {
        return db.collection("DoceFesta")
    }

    fun exibirDoceFestaDetalhe(nomeBolo: String?): Query{
        return db.collection("DetalheDoce").whereEqualTo("nome", nomeBolo)
    }
}