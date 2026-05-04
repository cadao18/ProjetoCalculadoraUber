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
import android.widget.LinearLayout

class SalvarActivity : AppCompatActivity() {

    private lateinit var banco: SalvarBanco

    private lateinit var txtTotalMes: TextView
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


        txtTotalMes = findViewById(R.id.txtTotalMes)

        btnVer = findViewById(R.id.btnVer)
        val container = findViewById<LinearLayout>(R.id.containerDados)

        val item = TextView(this)
        item.text = "17/04/2026   R$220.00"

        container.addView(item)

        btnVer.setOnClickListener {
            lifecycleScope.launch {

                val lista = withContext(Dispatchers.IO) {
                    banco.salvarDao().listarDias()
                }

                val container = findViewById<LinearLayout>(R.id.containerDados)
                container.removeAllViews() // limpa antes

                if (lista.isNotEmpty()) {

                    val totalMes = lista.sumOf { it.lucro }
                    txtTotalMes.text = "TOTAL DO MÊS: R$ %.2f".format(totalMes)

                    lista.forEach { dia ->
                        val item = TextView(this@SalvarActivity)
                        item.text = "${dia.data}   R$ %.2f".format(dia.lucro)
                        item.setTextColor(getColor(R.color.white))
                        item.textSize = 18f

                        container.addView(item)
                    }
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