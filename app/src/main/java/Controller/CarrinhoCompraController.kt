package Controller
import Model.CarrinhoCompra
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class CarrinhoCompraController {

    private val db = FirebaseFirestore.getInstance()

    fun adicionarCarrinhoCompra(carrinhoCompra: CarrinhoCompra, onResult: (Boolean, String?) -> Unit){
        val colecao = db.collection("CarrinhoCompras")
        colecao.add(carrinhoCompra).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onResult(true, null)
            } else {
                onResult(false, task.exception?.message)
            }
        }
    }

    fun listarPorEmailUsuario(email: String?): Query{
        return db.collection("CarrinhoCompras").whereEqualTo("email", email)
    }
}