package com.example.ptojetouber

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SalvarActivity : AppCompatActivity() {

    private lateinit var banco: SalvarBanco

    private lateinit var txtDataLucro: TextView
    private lateinit var txtTotalMes: TextView
    private lateinit var txtDetalhes: TextView
    private lateinit var btnVer: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_salvar)

        banco = Room.databaseBuilder(
            applicationContext,
            SalvarBanco::class.java,
            "banco_uber"
        ).build()

        txtDataLucro = findViewById(R.id.txtDataLucro)
        txtTotalMes = findViewById(R.id.txtTotalMes)
        txtDetalhes = findViewById(R.id.txtDetalhes)
        btnVer = findViewById(R.id.btnVer)

        btnVer.setOnClickListener {
            lifecycleScope.launch {

                val lista = withContext(Dispatchers.IO) {
                    banco.salvarDao().listarDias()
                }

                if (lista.isNotEmpty()) {

                    val ultimo = lista.last()
                    val totalMes = lista.sumOf { it.lucro }

                    txtDataLucro.text = "${ultimo.data}   R$ %.2f".format(ultimo.lucro)

                    txtTotalMes.text = "TOTAL DO MÊS: R$ %.2f".format(totalMes)

                    txtDetalhes.text =
                        "Valor por hora: R$ %.2f\nCombustível: R$ %.2f"
                            .format(ultimo.porHora, ultimo.combustivel)
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}