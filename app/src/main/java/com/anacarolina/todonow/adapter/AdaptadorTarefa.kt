package com.anacarolina.todonow.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.anacarolina.todonow.R
import com.anacarolina.todonow.tarefa_class

class AdaptadorTarefa(private val cardItems: List<tarefa_class>) : RecyclerView.Adapter<AdaptadorTarefa.CardViewHolder>() {

    // ViewHolder que representa cada item do RecyclerView
    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.findViewById(R.id.edit_Titulo)
        val textViewDescription: TextView = itemView.findViewById(R.id.edit_descricao)
    }

    // inflar o layout do item da lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val cardView = LayoutInflater.from(parent.context).inflate(R.layout.tarefa_item, parent, false)
        return CardViewHolder(cardView)
    }

    // Associa os dados de cada item à View do ViewHolder
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val item = cardItems[position]
        holder.textViewTitle.text = item.toString()
        holder.textViewDescription.text = item.toString()
    }

    //retorna o número total de ites na lista
    override fun getItemCount(): Int {
        return cardItems.size
    }
}


