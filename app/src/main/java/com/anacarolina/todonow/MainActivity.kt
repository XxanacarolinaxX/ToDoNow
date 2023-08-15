package com.anacarolina.todonow

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anacarolina.todonow.adapter.AdaptadorTarefa
import com.anacarolina.todonow.databinding.ActivityMainBinding
import com.anacarolina.todonow.databinding.CardAltaPrioridadeBinding
import com.anacarolina.todonow.databinding.CardMediaPrioridadeBinding
import com.anacarolina.todonow.databinding.CardBaixaPrioridadeBinding
import com.anacarolina.todonow.databinding.CardSemPrioridadeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adaptadorTarefa: AdaptadorTarefa
    private val listaTarefas: MutableList<Tarefa> = mutableListOf()

    // Binding dos tipos de cards
    private lateinit var cardBaixa: CardBaixaPrioridadeBinding
    private lateinit var cardMedia: CardMediaPrioridadeBinding
    private lateinit var cardAlta: CardAltaPrioridadeBinding
    private lateinit var cardSemPrioridade: CardSemPrioridadeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Esconde a supportActionBar
        supportActionBar?.hide()

        // Configuração do botão flutuante
        FloatingActionButton()

        // Configuração do RecyclerView para exibir as tarefas
        RecyclerView()

        // Inicialização dos binding para os cards de diferentes prioridades
        initializeCardBindings()
    }

    private fun FloatingActionButton() {
        val fab: FloatingActionButton = binding.fabButton
        fab.setOnClickListener {

            // Inicializa a tela de adição de novas tarefas
            startNovaTarefaActivity()
        }
    }

    private fun startNovaTarefaActivity() {
        meuActivityResultLauncher.launch(Intent(this, NovasTarefas::class.java))
    }

    // Configura o RecyclerView para exibir as tarefas
    private fun RecyclerView() {
        val recyclerView: RecyclerView = binding.recylerViewTarefas
        recyclerView.layoutManager = LinearLayoutManager(this)
        adaptadorTarefa = AdaptadorTarefa(listaTarefas)
        recyclerView.adapter = adaptadorTarefa
    }

    private val meuActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                if (data != null) {
                    processarResultado(data)
                }
            }
        }

    private fun processarResultado(data: Intent) {
        val novaTarefa = data.getParcelableExtra<Tarefa>("novaTarefa")
        val prioridadeTarefa = data.getStringExtra("prioridade") ?: ""

        if (novaTarefa != null) {
            listaTarefas.add(novaTarefa)
            adaptadorTarefa.notifyDataSetChanged()
        }

        // Exibe o card apropriado de acordp com a prioridade da tarefa
        exibirCard(prioridadeTarefa)
    }

    private fun exibirCard(prioridadeTarefa: String) {
        cardBaixa.root.visibility = View.GONE
        cardMedia.root.visibility = View.GONE
        cardAlta.root.visibility = View.GONE
        cardSemPrioridade.root.visibility = View.GONE

        when (prioridadeTarefa) {
            "Baixa" -> cardBaixa.root.visibility = View.VISIBLE
            "Media" -> cardMedia.root.visibility = View.VISIBLE
            "Alta" -> cardAlta.root.visibility = View.VISIBLE
            else -> cardSemPrioridade.root.visibility = View.VISIBLE
        }
    }

    // Inicializa os bindings para os diferentes tipos de cards
    private fun initializeCardBindings() {
        cardBaixa = CardBaixaPrioridadeBinding.inflate(layoutInflater)
        cardMedia = CardMediaPrioridadeBinding.inflate(layoutInflater)
        cardAlta = CardAltaPrioridadeBinding.inflate(layoutInflater)
        cardSemPrioridade = CardSemPrioridadeBinding.inflate(layoutInflater)
    }
}
