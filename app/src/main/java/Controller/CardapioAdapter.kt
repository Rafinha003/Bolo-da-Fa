package Controller

import Model.Cardapio
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bolo_fa.databinding.ItemCardapioBinding


class CardapioAdapter(private var lista: MutableList<Cardapio>) : RecyclerView.Adapter<CardapioAdapter.CardapioViewHolder>() {


    class CardapioViewHolder(val binding: ItemCardapioBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardapioViewHolder {
        val binding = ItemCardapioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardapioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardapioViewHolder, position: Int) {
        val cardapioItem = lista[position]
        holder.binding.nomeOpcaoCardapio.text = cardapioItem.nome
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun atualizarLista(novasTarefas: List<Cardapio>){
        lista.addAll(novasTarefas)
        notifyDataSetChanged()
    }

}
