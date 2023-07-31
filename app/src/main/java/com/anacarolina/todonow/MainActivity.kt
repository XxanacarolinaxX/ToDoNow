package com.anacarolina.todonow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anacarolina.todonow.adapter.AdaptadorTarefa
import com.anacarolina.todonow.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cardView: CardView
    private lateinit var adaptadorTarefa: AdaptadorTarefa
    private lateinit var listaTarefas: MutableList<tarefa_class>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Esconder supportActionBar
        supportActionBar?.hide()

        //Navegação da MainActivity para NovasTarefas com fabButton
        binding.fabButton.setOnClickListener {
            val intent = Intent(this, NovasTarefas::class.java)
            startActivity(intent)
        }

        //lista de tarefas
        val listaTarefas = mutableListOf(
            tarefa_class("titulo", "descrição", "prioridade")
        )

        //recupera os dados passados
        val titulo = intent.getStringExtra("titulo")
        val descricao = intent.getStringExtra("descrição")
        val prioridade = intent.getStringExtra("prioridade")!!

        //Inicializa o adaptador
        adaptadorTarefa = AdaptadorTarefa(listaTarefas)

        // RecyclerView para exibir cardViews
        val recyclerView: RecyclerView = findViewById(R.id.recylerView_tarefas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adaptadorTarefa



        // Cria um novo objeto Tarefa com as informações recebidas
        if (!titulo.isNullOrEmpty() && !descricao.isNullOrEmpty()) {
            val novatarefa = tarefa_class(titulo, descricao, prioridade)

            // Adiciona a nova tarefa à lista de tarefas
            adaptadorTarefa.add(novatarefa)
        }

        // Notifique o adaptador sobre a mudança na lista de tarefas
        adaptadorTarefa.notifyDataSetChanged()


        //exibe as informações dos editTexts
        cardView = findViewById(R.id.cardView)
    }

    //exibir informações no cardView
    override fun onResume() {
        super.onResume()
    }
}

