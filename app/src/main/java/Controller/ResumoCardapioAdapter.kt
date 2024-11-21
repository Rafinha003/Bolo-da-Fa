package Controller

import Model.CarrinhoCompra
import android.view.LayoutInflater
import android.view.ViewGroup
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
        }
    }

    fun atualizarCarrinho(carrinho: List<CarrinhoCompra>){
        lista.clear()
        lista.addAll(carrinho)
        notifyDataSetChanged()
    }
}
