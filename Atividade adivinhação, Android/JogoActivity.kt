package br.ulbra.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs
import kotlin.random.Random

class JogoActivity : AppCompatActivity() {

    private var numeroSecreto = 0
    private var tentativas = 0

    private var nome = ""
    private var limite = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)

        // Receber os dados enviados pela MainActivity
        nome = intent.getStringExtra("nome") ?: ""
        limite = intent.getIntExtra("limite", 50)

        // Sortear número secreto
        numeroSecreto = Random.nextInt(1, limite + 1)

        val txtBoasVindas = findViewById<TextView>(R.id.txtBoasVindas)
        val edtPalpite = findViewById<EditText>(R.id.edtPalpite)
        val btnChutar = findViewById<Button>(R.id.btnChutar)
        val txtResultado = findViewById<TextView>(R.id.txtResultado)
        val txtTentativas = findViewById<TextView>(R.id.txtTentativas)
        val btnJogarNovamente = findViewById<Button>(R.id.btnJogarNovamente)

        txtBoasVindas.text = "$nome, pensei num número de\n1 a $limite!"

        txtTentativas.text = "Tentativas: 0"

        btnChutar.setOnClickListener {

            val textoPalpite = edtPalpite.text.toString()

            if (textoPalpite.isEmpty()) {
                Toast.makeText(
                    this,
                    "Digite um número!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val palpite = textoPalpite.toIntOrNull()

            if (palpite == null) {
                Toast.makeText(
                    this,
                    "Digite apenas números!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (palpite < 1 || palpite > limite) {
                Toast.makeText(
                    this,
                    "Digite um número entre 1 e $limite!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            tentativas++

            val diferenca = abs(palpite - numeroSecreto)
            val limiteQuente = limite * 0.10

            if (palpite == numeroSecreto) {

                txtResultado.text =
                    "$nome acertou em $tentativas tentativas!"

                btnChutar.isEnabled = false
                edtPalpite.isEnabled = false

                btnJogarNovamente.visibility = Button.VISIBLE

            } else {

                val temperatura = if (diferenca <= limiteQuente) {
                    "Quente!"
                } else {
                    "Frio!"
                }

                val direcao = if (numeroSecreto > palpite) {
                    "O número é MAIOR."
                } else {
                    "O número é MENOR."
                }

                txtResultado.text = "$temperatura\n$direcao"

                txtTentativas.text = "Tentativas: $tentativas"
            }

            edtPalpite.text.clear()
        }

        btnJogarNovamente.setOnClickListener {
            finish()
        }
    }
}