package com.anacarolina.todonow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.anacarolina.todonow.databinding.ActivityMainBinding
import com.google.android.material.appbar.MaterialToolbar

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

        //Navegação da MainActivity para NovasTarefas com fabButton
        binding.fabButton.setOnClickListener {
            val intent = Intent(this, NovasTarefas::class.java)
            startActivity(intent)
        }

        //recyclerView para exibir cardViews

        //exibe as informações dos editTexts
        cardView = findViewById(R.id.cardView)
    }

    //exibir informações no cardView
    override fun onResume() {
        super.onResume()

        val titulo = intent.getStringExtra("titulo")
        val descricao = intent.getStringExtra("descricao")

        if (titulo != null && descricao != null) {
            binding.cardView.visibility = View.VISIBLE

            val tituloTextView: TextView = binding.cardView.findViewById(R.id.cardView_titulo)
            val descricaoTextView: TextView = binding.cardView.findViewById(R.id.cardView_descricao)

            tituloTextView.text = titulo
            descricaoTextView.text = descricao
        } else {
            cardView.visibility = View.GONE
        }

    }

}
