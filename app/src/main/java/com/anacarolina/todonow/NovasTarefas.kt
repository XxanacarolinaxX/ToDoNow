package com.anacarolina.todonow

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import com.anacarolina.todonow.adapter.AdaptadorTarefa
import com.anacarolina.todonow.databinding.ActivityNovasTarefasBinding

class NovasTarefas : AppCompatActivity() {

    private lateinit var binding: ActivityNovasTarefasBinding
    private val listaTarefas: MutableList<Tarefa> = mutableListOf()
    private lateinit var adaptadorTarefa: AdaptadorTarefa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNovasTarefasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Esconde a supportActionBar
        supportActionBar?.hide()

        // Configuração do RadioGroup para os radioButtons
        RadioGroup()

        // Configuração do botão "Salvar"
        SalvarButton()
    }

    private fun RadioGroup() {
        val radioGroup: RadioGroup = binding.radioGroup

        // Listener para controlar qual radioButton está selecionado
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            for (i in 0 until group.childCount) {
                val radioButton: RadioButton = group.getChildAt(i) as RadioButton
                radioButton.isChecked = radioButton.id == checkedId
            }
        }
    }

    private fun SalvarButton() {

        // Evento de Click do botõ "Salvar"
        binding.buttonSalvar.setOnClickListener {
            val tituloNT = binding.editTitulo.text.toString()
            val descricaoNT = binding.editDescricao.text.toString()

            // Determina a prioridade com base no radioButton selecionado
            val prioridadeNT = when (binding.radioGroup.checkedRadioButtonId) {
                R.id.radioButton1 -> "Baixa"
                R.id.radioButton2 -> "Media"
                R.id.radioButton3 -> "Alta"
                else -> ""
            }

            // Cria um Intent para retornar a tarefa como resultado
            val returnIntent = Intent()
            val novaTarefa = Tarefa(tituloNT, descricaoNT, prioridadeNT)
            returnIntent.putExtra("novaTarefa", novaTarefa)
            returnIntent.putExtra("prioridade", prioridadeNT)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}
