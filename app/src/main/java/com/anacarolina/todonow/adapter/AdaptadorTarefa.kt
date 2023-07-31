package com.anacarolina.todonow.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anacarolina.todonow.R
import com.anacarolina.todonow.Tarefa
import com.anacarolina.todonow.tarefa_class

class AdaptadorTarefa(private val listaTarefas: MutableList<Tarefa>) : RecyclerView.Adapter<AdaptadorTarefa.CardViewHolder>() {

    // ViewHolder que representa cada item do RecyclerView
    inner class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tituloTextView: TextView = view.findViewById(R.id.cardView_titulo)
        val descricaoTextView: TextView = view.findViewById(R.id.cardView_descricao)
    }

    // Inflar o layout do item da lista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val cardView = LayoutInflater.from(parent.context).inflate(R.layout.tarefa_item, parent, false)
        return CardViewHolder(cardView)
    }

    // Associa os dados de cada item à View do ViewHolder
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val tarefa = listaTarefas[position]

        // Define os dados da tarefa nos elementos do CardView
        holder.tituloTextView.text = tarefa.titulo
        holder.descricaoTextView.text = tarefa.descricao
    }

    // Retorna o número total de itens na lista
    override fun getItemCount(): Int {
        return listaTarefas.size
    }

    // Método para adicionar uma nova tarefa à lista
    fun add(tarefa: Tarefa) {
        listaTarefas.add(tarefa)
        notifyDataSetChanged()
    }
}

