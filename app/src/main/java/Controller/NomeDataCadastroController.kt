package Controller

import Model.NomeDataCadastro
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class NomeDataCadastroController {

    private val db = FirebaseFirestore.getInstance()

    fun salvarNomeDataCadastro(nomeDataCadastro: NomeDataCadastro, onResult: (Boolean, String?) -> Unit){
        val colecao = db.collection("NomeUsuario")

        colecao.add(nomeDataCadastro).addOnCompleteListener{ task ->
            if(task.isSuccessful){
                onResult(true, null)
            }else{
                onResult(false, null)
            }
        }
    }
}