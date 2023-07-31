package com.anacarolina.todonow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.anacarolina.todonow.adapter.AdaptadorTarefa
import com.anacarolina.todonow.databinding.ActivityNovasTarefasBinding

class NovasTarefas : AppCompatActivity() {
    private lateinit var binding: ActivityNovasTarefasBinding
    private val listaTarefas: MutableList<tarefa_class> = mutableListOf()
    private lateinit var adaptadorTarefa: AdaptadorTarefa

    private lateinit var semPrioridade: ImageView
    private lateinit var prioridadeBaixa: ImageView
    private lateinit var prioridadeMedia: ImageView
    private lateinit var prioridadeAlta: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNovasTarefasBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Inicializa as variáveis das prioridades
        prioridadeBaixa = findViewById(R.id.drawable_baixa)
        prioridadeMedia = findViewById(R.id.drawable_media)
        prioridadeAlta = findViewById(R.id.drawable_alta)
        semPrioridade = findViewById(R.id.drawable_semprioridade)

        //Esconder supportActionBar
        supportActionBar?.hide()

        // Inicializa o adaptador de tarefas
        adaptadorTarefa = AdaptadorTarefa(listaTarefas)

        //click do radioButton
        val radioGroup: RadioGroup = binding.radioGroup

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            for (i in 0 until group.childCount) {
                val radioButton: RadioButton = group.getChildAt(i) as RadioButton
                radioButton.isChecked = radioButton.id == checkedId
            }
        }

        //exibe a imagem do circulo de cada prioridade
        val radioButtonbBaixa: RadioButton = findViewById(R.id.radioButton1)
        val radioButtonbMedia: RadioButton = findViewById(R.id.radioButton2)
        val radioButtonbAlta: RadioButton = findViewById(R.id.radioButton3)

        radioButtonbBaixa.setOnClickListener {
            prioridadeBaixa.visibility = ImageView.VISIBLE
            prioridadeMedia.visibility = ImageView.INVISIBLE
            prioridadeAlta.visibility = ImageView.INVISIBLE
        }

        radioButtonbMedia.setOnClickListener {
            prioridadeBaixa.visibility = ImageView.INVISIBLE
            prioridadeMedia.visibility = ImageView.VISIBLE
            prioridadeAlta.visibility = ImageView.INVISIBLE
        }
        radioButtonbAlta.setOnClickListener {
            prioridadeBaixa.visibility = ImageView.INVISIBLE
            prioridadeMedia.visibility = ImageView.INVISIBLE
            prioridadeAlta.visibility = ImageView.VISIBLE
        }

        //apenas um permanecer clicado
        for (i in 0 until radioGroup.childCount) {
            val radioButton: RadioButton = radioGroup.getChildAt(i) as RadioButton
            radioButton.setOnClickListener {
                for (j in 0 until radioGroup.childCount) {
                    val outroRadioButton: RadioButton = radioGroup.getChildAt(j) as RadioButton
                    outroRadioButton.isChecked = outroRadioButton == radioButton
                }
            }
        }

        //envia informações entre atividades e passa os valores dos editTexts para a MainActivity
        binding.buttonSalvar.setOnClickListener {
            val tituloNT = binding.editTitulo.text.toString()
            val descricaoNT = binding.editDescricao.text.toString()
            val prioridadeNT = when (binding.radioGroup.checkedRadioButtonId) {
                R.id.radioButton1 -> "Baixa"
                R.id.radioButton2 -> "Média"
                R.id.radioButton3 -> "Alta"
                else -> ""
            }

            if (prioridadeNT.isEmpty()) {
                semPrioridade.visibility = View.VISIBLE
                prioridadeBaixa.visibility = View.INVISIBLE
                prioridadeMedia.visibility = View.INVISIBLE
                prioridadeAlta.visibility = View.INVISIBLE
            } else {
                //passa as informações para a MainActivity
                val novatarefa = tarefa_class(tituloNT, descricaoNT, prioridadeNT)
                listaTarefas.add(novatarefa) // Adiciona a tarefa à lista antes de passá-la para o adaptador
                adaptadorTarefa.notifyDataSetChanged() // Notifica o adaptador sobre a mudança nos dados
            }
        }
    }

    // Função para preencher a lista de tarefas (pode ser chamada em outros lugares conforme sua lógica)
    private fun preencherListaTarefas() {
        listaTarefas.add(tarefa_class("Exemplo 1", "Descrição de exemplo 1", "Baixa"))
        listaTarefas.add(tarefa_class("Exemplo 2", "Descrição de exemplo 2", "Média"))
        listaTarefas.add(tarefa_class("Exemplo 3", "Descrição de exemplo 3", "Alta"))
    }
}
