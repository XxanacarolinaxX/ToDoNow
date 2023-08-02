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
import com.anacarolina.todonow.databinding.CardBaixaPrioridadeBinding
import com.anacarolina.todonow.databinding.CardMediaPrioridadeBinding
import com.anacarolina.todonow.databinding.CardSemPrioridadeBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adaptadorTarefa: AdaptadorTarefa
    private lateinit var listaTarefas: MutableList<Tarefa>

    private lateinit var cardBaixa: CardBaixaPrioridadeBinding
    private lateinit var cardMedia: CardMediaPrioridadeBinding
    private lateinit var cardAlta: CardAltaPrioridadeBinding
    private lateinit var cardSemPrioridade: CardSemPrioridadeBinding

    private val meuActivityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            if (data != null) {
                val novaTarefa = data.getParcelableExtra<Tarefa>("novaTarefa") as Tarefa

                if (novaTarefa != null) {
                    listaTarefas.add(novaTarefa)
                    adaptadorTarefa.notifyDataSetChanged()
                }

                // Verifica a prioridade da tarefa e exibe no CardView apropriado
                when (novaTarefa.prioridade) {
                    "Baixa" -> {
                        cardBaixa.root.visibility = View.VISIBLE
                        cardMedia.root.visibility = View.GONE
                        cardAlta.root.visibility = View.GONE
                        cardSemPrioridade.root.visibility = View.GONE
                    }
                    "Media" -> {
                        cardBaixa.root.visibility = View.GONE
                        cardMedia.root.visibility = View.VISIBLE
                        cardAlta.root.visibility = View.GONE
                        cardSemPrioridade.root.visibility = View.GONE
                    }
                    "Alta" -> {
                        cardBaixa.root.visibility = View.GONE
                        cardMedia.root.visibility = View.GONE
                        cardAlta.root.visibility = View.VISIBLE
                        cardSemPrioridade.root.visibility = View.GONE
                    }
                    else -> {
                        cardBaixa.root.visibility = View.GONE
                        cardMedia.root.visibility = View.GONE
                        cardAlta.root.visibility = View.GONE
                        cardSemPrioridade.root.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Esconder supportActionBar
        supportActionBar?.hide()

        // Navegação da MainActivity para NovasTarefas com fabButton
        val fab: FloatingActionButton = findViewById(R.id.fabButton)
        fab.setOnClickListener {
            meuActivityResultLauncher.launch(Intent(this, NovasTarefas::class.java))
        }

        cardBaixa = CardBaixaPrioridadeBinding.inflate(layoutInflater)
        cardMedia = CardMediaPrioridadeBinding.inflate(layoutInflater)
        cardAlta = CardAltaPrioridadeBinding.inflate(layoutInflater)
        cardSemPrioridade = CardSemPrioridadeBinding.inflate(layoutInflater)

        // Lista de tarefas
        listaTarefas = mutableListOf()

        // RecyclerView para exibir cardViews
        val recyclerView: RecyclerView = findViewById(R.id.recylerView_tarefas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adaptadorTarefa = AdaptadorTarefa(listaTarefas)
        recyclerView.adapter = adaptadorTarefa
    }
}
