package com.anacarolina.todonow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import com.anacarolina.todonow.adapter.AdaptadorTarefa
import com.anacarolina.todonow.databinding.ActivityNovasTarefasBinding
import com.anacarolina.todonow.cardItems

class NovasTarefas : AppCompatActivity() {
    private lateinit var binding: ActivityNovasTarefasBinding
    private val CardItems: MutableList<cardItems> = mutableListOf()
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

        //Esconder supportActionBar
        supportActionBar?.hide()



        //click do radioButton
        val radioGroup: RadioGroup = binding.radioGroup

        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            for (i in 0 until group.childCount) {
                val radioButton: RadioButton = group.getChildAt(i) as RadioButton
                radioButton.isChecked = radioButton.id == checkedId
            }
        }

        // Inicializa as variáveis das prioridades
        prioridadeBaixa = findViewById(R.id.drawable_baixa)
        prioridadeMedia = findViewById(R.id.drawable_media)
        prioridadeAlta = findViewById(R.id.drawable_alta)
        semPrioridade = findViewById(R.id.drawable_semprioridade)

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


        //envia informacoes entre atividades e passa os valores dos editTexts para a MainActivity
        binding.buttonSalvar.setOnClickListener {
            val tituloNT = binding.editTitulo.text.toString()
            val descricaoNT = binding.editDescricao.text.toString()
            val prioridadeNT = when (binding.radioGroup.checkedRadioButtonId) {
                R.id.radioButton1 -> "Baixa"
                R.id.radioButton2 -> "Média"
                R.id.radioButton3 -> "Alta"
                else -> {
                    ""
                }
            }

            if (prioridadeNT.isEmpty()) {
                semPrioridade.visibility = View.VISIBLE
                prioridadeBaixa.visibility = View.INVISIBLE
                prioridadeMedia.visibility = View.INVISIBLE
                prioridadeAlta.visibility = View.INVISIBLE
            }

            //passa as informções para a MainActivity
            val novatarefa = tarefa_class(tituloNT, descricaoNT, prioridadeNT)
            adaptadorTarefa.add(novatarefa)

        }

    }
}
