package com.example.ptojetouber

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val Calcular = findViewById<Button>(R.id.btnCalcular)

        val ganho = findViewById<EditText>(R.id.EditTextGanhoTotal)
        val km = findViewById<EditText>(R.id.EditTextKM)
        val combustivel = findViewById<EditText>(R.id.EditTextCombustivel)
        val alimentacao = findViewById<EditText>(R.id.EditTextAlimentacao)
        val horas = findViewById<EditText>(R.id.EditTextHoras)
        val outros = findViewById<EditText>(R.id.EditTextOutros)
        val consumo = findViewById<EditText>(R.id.editTextConsumo)

        Calcular.setOnClickListener {

            val ganhoText = ganho.text.toString()
            val kmText = km.text.toString()
            val combustivelText = combustivel.text.toString()
            val alimentacaoText = alimentacao.text.toString()
            val horasText = horas.text.toString()
            val outrosText = outros.text.toString()
            val consumoText = consumo.text.toString()

            var erro = false

            if (ganhoText.isEmpty()) {
                ganho.error = "Campo obrigatório"
                erro = true
            }

            if (kmText.isEmpty()) {
                km.error = " campo obrigatótio"
                erro = true
            }

            if (combustivelText.isEmpty()) {
                combustivel.error = "campo obrigatótio"
                erro = true
            }

            if (alimentacaoText.isEmpty()) {
                alimentacao.error = "campo obrigatório"
                erro = true
            }

            if (consumoText.isEmpty()) {
                consumo.error = "campo obrigatório"
                erro = true
            }

            if (outrosText.isEmpty()) {
                outros.error = "campo obrigatório"
                erro = true
            }

            val horasNum = horasText.toIntOrNull()

            if (horasText.isEmpty()) {
                horas.error = "Campo obrigatório"
                erro = true
            } else if (horasNum == null) {
                horas.error = "Somente números"
                erro = true
            } else if (horasNum > 12) {
                horas.error = "Máximo 12 horas"
                erro = true
            }

            if (erro) return@setOnClickListener

            val ganhoNum = ganhoText.replace(",", ".").toDoubleOrNull() ?: 0.0
            val kmNum = kmText.replace(",", ".").toDoubleOrNull() ?: 0.0
            val combustivelNum = combustivelText.replace(",", ".").toDoubleOrNull() ?: 0.0
            val alimentacaoNum = alimentacaoText.replace(",", ".").toDoubleOrNull() ?: 0.0
            val outrosNum = outrosText.replace(",", ".").toDoubleOrNull() ?: 0.0
            val consumoNum = consumoText.replace(",", ".").toDoubleOrNull() ?: 0.0

            val litrosUsados = if (consumoNum != 0.0) {
                kmNum / consumoNum
            } else {
                0.0
            }

            val gastoCombustivel = litrosUsados * combustivelNum

            val custoPorKm = 0.50

            val custoManutencao = kmNum * custoPorKm

            val gastos = gastoCombustivel + alimentacaoNum + outrosNum + custoManutencao

            val lucro = ganhoNum - gastos

            val ganhoPorHora = if (horasNum != null && horasNum > 0) {
                lucro / horasNum.toDouble()
            } else {
                0.0
            }

            if (erro) return@setOnClickListener

            val intent = Intent(this, ResultadoActivity::class.java)

            intent.putExtra("lucro", lucro)
            intent.putExtra("ganhoHora", ganhoPorHora)

            startActivity(intent)
        }

        val btnLimpar = findViewById<Button>(R.id.btnLimpar)

        btnLimpar.setOnClickListener {

            ganho.text.clear()
            km.text.clear()
            combustivel.text.clear()
            alimentacao.text.clear()
            horas.text.clear()
            outros.text.clear()
            consumo.text.clear()
        }
    }
}