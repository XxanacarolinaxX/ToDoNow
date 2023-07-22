package com.anacarolina.todonow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import com.anacarolina.todonow.databinding.ActivityNovasTarefasBinding

class NovasTarefas : AppCompatActivity() {
    private lateinit var binding: ActivityNovasTarefasBinding

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

        //exibir nivel de prioridade na MainActivity


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
                Toast.makeText(this, "Adicione uma prioridade para a sua tarefa",
                    Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("exibirCardView", true)
                intent.putExtra("titulo", tituloNT)
                intent.putExtra("descricao", descricaoNT)
                intent.putExtra("prioridade", prioridadeNT)
                startActivity(intent)
            }
        }
    }
}
