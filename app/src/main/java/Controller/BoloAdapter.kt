package Controller

import Model.Cardapio
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bolo_fa.DetalheItemCardapio
import com.example.bolo_fa.databinding.ItemCardapioBinding


class BoloAdapter(private var lista: MutableList<Cardapio>) : RecyclerView.Adapter<BoloAdapter.CardapioViewHolder>() {


    class CardapioViewHolder(val binding: ItemCardapioBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardapioViewHolder {
        val binding = ItemCardapioBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardapioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardapioViewHolder, position: Int) {
        val cardapioItem = lista[position]
        holder.binding.nomeOpcaoCardapio.text = cardapioItem.nome

        holder.binding.btnVisualizar.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetalheItemCardapio::class.java)

            intent.putExtra( "nome", cardapioItem.nome)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }


    fun atualizarListaBolo(novasTarefas: List<Cardapio>){
        lista.addAll(novasTarefas)
        notifyDataSetChanged()
    }
}