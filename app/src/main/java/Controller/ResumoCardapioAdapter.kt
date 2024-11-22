package Controller

import Model.CarrinhoCompra
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bolo_fa.databinding.ResumoCardapioAdapterBinding

class ResumoCardapioAdapter(private var lista: MutableList<CarrinhoCompra>) : RecyclerView.Adapter<ResumoCardapioAdapter.ResumoCardapioViewHolder>() {

    class ResumoCardapioViewHolder(val binding: ResumoCardapioAdapterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResumoCardapioViewHolder {
        val binding = ResumoCardapioAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ResumoCardapioViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return lista.size
    }


    override fun onBindViewHolder(holder: ResumoCardapioViewHolder, position: Int) {
        if(getItemCount() > 0) {
            val item = lista[position]
            holder.binding.produto.text = item.produto
            holder.binding.productPrice.text = item.preco
            holder.binding.productQuantity.text = "Quantidade: " + item.quantidade

            holder.binding.addButton.setOnClickListener{
                val carrinhoCompraController = CarrinhoCompraController()

                carrinhoCompraController.adicionarMaisUmQuantidade(item.produto){ sucesso, erro ->
                    if(sucesso){
                        Toast.makeText(holder.itemView.context, "Adicionado mais uma quantidade do produto", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(holder.itemView.context, "Erro ao adicionar o item ", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            holder.binding.removeButton.setOnClickListener{
                val carrinhoCompraController = CarrinhoCompraController()

                carrinhoCompraController.removerUmnaQuantidade(item.produto){sucesso, erro ->
                    if(sucesso){
                        Toast.makeText(holder.itemView.context, "Removido 1 quantidade desse produto", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(holder.itemView.context, "Falha ao remover quantidade desse produto", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            holder.binding.deleteButton.setOnClickListener{
                val carrinhoController = CarrinhoCompraController()

                carrinhoController.deletarProdutoCarrinho(item.produto){sucesso, erro ->
                    if(sucesso){
                        Toast.makeText(holder.itemView.context, "Item removido com sucesso", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(holder.itemView.context, "Erro ao remover", Toast.LENGTH_SHORT).show()
                    }
                }
                lista.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, lista.size)
            }

        }
    }

    fun atualizarCarrinho(carrinho: List<CarrinhoCompra>){
        lista.clear()
        lista.addAll(carrinho)
        notifyDataSetChanged()
    }
}
