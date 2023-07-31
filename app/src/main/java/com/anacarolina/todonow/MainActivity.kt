package com.anacarolina.todonow

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anacarolina.todonow.adapter.AdaptadorTarefa
import com.anacarolina.todonow.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private val CODIGO_NOVA_TAREFA = 1
    private lateinit var binding: ActivityMainBinding
    private lateinit var adaptadorTarefa: AdaptadorTarefa
    private lateinit var listaTarefas: MutableList<Tarefa>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Esconder supportActionBar
        supportActionBar?.hide()

        // Navegação da MainActivity para NovasTarefas com fabButton
        val fab: FloatingActionButton = findViewById(R.id.fabButton)
        fab.setOnClickListener {
            val intent = Intent(this, NovasTarefas::class.java)
            startActivityForResult(intent, CODIGO_NOVA_TAREFA)
        }

        // Lista de tarefas
        listaTarefas = mutableListOf<Tarefa>()

        // Recupera os dados passados
        val titulo = intent.getStringExtra("titulo")
        val descricao = intent.getStringExtra("descricao")
        val prioridade = intent.getStringExtra("prioridade") ?: ""

        // Cria um novo objeto Tarefa com as informações recebidas e adiciona à lista de tarefas
        if (!titulo.isNullOrEmpty() && !descricao.isNullOrEmpty()) {
            val novatarefa = Tarefa(titulo, descricao, prioridade)
            listaTarefas.add(novatarefa)
        }

        // Inicializa o adaptador
        adaptadorTarefa = AdaptadorTarefa(listaTarefas)

        // RecyclerView para exibir cardViews
        val recyclerView: RecyclerView = findViewById(R.id.recylerView_tarefas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adaptadorTarefa = AdaptadorTarefa(listaTarefas)
        recyclerView.adapter = adaptadorTarefa

    }

    // Exibir informações no cardView
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODIGO_NOVA_TAREFA && resultCode == Activity.RESULT_OK) {
            // Recebe a tarefa enviada pela NovasTarefas.kt
            val novaTarefa = data?.getParcelableExtra<Tarefa>("novaTarefa")

            // Verifica se a tarefa não é nula e a adiciona à lista de tarefas
            if (novaTarefa != null) {
                listaTarefas.add(novaTarefa)
                adaptadorTarefa.notifyDataSetChanged()
            }
        }
    }
}
