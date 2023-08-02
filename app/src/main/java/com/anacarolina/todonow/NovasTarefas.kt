package com.anacarolina.todonow

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.anacarolina.todonow.adapter.AdaptadorTarefa
import com.anacarolina.todonow.databinding.*

class NovasTarefas : AppCompatActivity() {
    private lateinit var binding: ActivityNovasTarefasBinding

    private val listaTarefas: MutableList<Tarefa> = mutableListOf()
    private lateinit var adaptadorTarefa: AdaptadorTarefa

    private lateinit var semPrioridade: ImageView
    private lateinit var prioridadeBaixa: ImageView
    private lateinit var prioridadeMedia: ImageView
    private lateinit var prioridadeAlta: ImageView

    private lateinit var altaPrioridadeBinding: CardAltaPrioridadeBinding
    private lateinit var mediaPrioridadeBinding: CardMediaPrioridadeBinding
    private lateinit var baixaPrioridadeBinding: CardBaixaPrioridadeBinding
    private lateinit var semPrioridadeBinding: CardSemPrioridadeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNovasTarefasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Esconder supportActionBar
        supportActionBar?.hide()

        // Inicializa o adaptador de tarefas
        adaptadorTarefa = AdaptadorTarefa(listaTarefas)

        // Inflar os layouts das tarefas
        altaPrioridadeBinding= CardAltaPrioridadeBinding.inflate(layoutInflater)
        mediaPrioridadeBinding = CardMediaPrioridadeBinding.inflate(layoutInflater)
        baixaPrioridadeBinding = CardBaixaPrioridadeBinding.inflate(layoutInflater)
        semPrioridadeBinding = CardSemPrioridadeBinding.inflate(layoutInflater)

        //Click do radioButton
        val radioGroup: RadioGroup = binding.radioGroup

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            for (i in 0 until group.childCount) {
                val radioButton: RadioButton = group.getChildAt(i) as RadioButton
                radioButton.isChecked = radioButton.id == checkedId
            }
        }

        val radioButtonbBaixa: RadioButton = findViewById(R.id.radioButton1)
        val radioButtonbMedia: RadioButton = findViewById(R.id.radioButton2)
        val radioButtonbAlta: RadioButton = findViewById(R.id.radioButton3)

        //Envia informações entre atividades e passa os valores dos editTexts para a MainActivity
        binding.buttonSalvar.setOnClickListener {
            val tituloNT = binding.editTitulo.text.toString()
            val descricaoNT = binding.editDescricao.text.toString()
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
            setResult(Activity.RESULT_OK, returnIntent)
            finish()

        }
    }
}
