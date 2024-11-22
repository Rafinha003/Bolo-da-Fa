package Controller
import Model.CarrinhoCompra
import android.widget.Toast
import com.example.bolo_fa.carrinho_compras
import com.google.firebase.firestore.FieldValue
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

    fun adicionarMaisUmQuantidade(produto: String?, onResult: (Boolean, String?) -> Unit) {
        val colecao = db.collection("CarrinhoCompras")

        colecao.whereEqualTo("produto", produto).get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    val document = result.documents.first()
                    val carrinhoId = document.id

                    colecao.document(carrinhoId).update("quantidade", FieldValue.increment(1))
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                onResult(true, null)
                            } else {
                                onResult(false, task.exception?.message)
                            }
                        }
                } else {
                    onResult(false, "Produto não encontrado no carrinho")
                }
            }
    }

    fun removerUmnaQuantidade(produto: String?, onResult: (Boolean, String?) -> Unit){
        val colecao = db.collection("CarrinhoCompras")

        colecao.whereEqualTo("produto", produto).get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    val document = result.documents.first()
                    val carrinhoId = document.id

                    colecao.document(carrinhoId).update("quantidade", FieldValue.increment(-1))
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                onResult(true, null)
                            } else {
                                onResult(false, task.exception?.message)
                            }
                        }
                } else {
                    onResult(false, "Produto não encontrado no carrinho")
                }
            }
    }

    fun deletarProdutoCarrinho(produto: String?, onResult: (Boolean, String?) -> Unit) {
        if (produto != null) {
            db.collection("CarrinhoCompras").whereEqualTo("produto", produto).get()
                .addOnSuccessListener { result ->
                    if (!result.isEmpty) {
                        val document = result.documents.first()

                        document.reference.delete().addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                onResult(true, null)
                            } else {
                                onResult(false, "Erro ao remover o item do carrinho")
                            }
                        }
                    }
                }
        }
    }
}