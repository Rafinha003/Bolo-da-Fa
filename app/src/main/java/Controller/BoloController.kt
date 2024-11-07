package Controller

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class BoloController {

    private val db = FirebaseFirestore.getInstance()

    fun listarTodosOsBolos(): Query {
        return db.collection("Bolo")
    }
}