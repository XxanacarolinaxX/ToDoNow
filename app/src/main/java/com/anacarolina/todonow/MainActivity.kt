package com.anacarolina.todonow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anacarolina.todonow.adapter.AdaptadorTarefa
import com.anacarolina.todonow.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar
import com.anacarolina.todonow.tarefa_class


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cardView: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Esconder supportActionBar
        supportActionBar?.hide()

        //personaliza a topAppBar
        val topAppBar: MaterialToolbar = findViewById(R.id.topAppBar)

        //lista de tarefas
        val listaTarefas = mutableListOf<tarefa_class>(
            tarefa_class("titulo", "descrição", "prioridade")
        )

        //recupera os dados passados
        val titulo = intent.getStringExtra("titulo")
        val descricao = intent.getStringExtra("descrição")
        val prioridade = intent

        // adicionar dados a lista de tarefas
        if (!titulo.isNullOrEmpty() && !descricao.isNullOrEmpty()){
            listaTarefas.add(tarefa_class(titulo,descricao))
        }


        //Navegação da MainActivity para NovasTarefas com fabButton
        binding.fabButton.setOnClickListener {
            val intent = Intent(this, NovasTarefas::class.java)
            startActivity(intent)
        }

        //recyclerView para exibir cardViews
        val recyclerView: RecyclerView = findViewById(R.id.recylerView_tarefas)

        val adaptadorTarefa = AdaptadorTarefa(listaTarefas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adaptadorTarefa


        //exibe as informações dos editTexts
        cardView = findViewById(R.id.cardView)
    }

    //exibir informações no cardView
    override fun onResume() {
        super.onResume()


    }

}
