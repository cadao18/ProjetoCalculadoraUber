package com.example.ptojetouber

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ptojetouber.SalvarBanco
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import kotlinx.coroutines.launch



class ResultadoActivity : AppCompatActivity() {

    private lateinit var banco: SalvarBanco
    private lateinit var btnEtanolGasolina: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        banco = Room.databaseBuilder(
            applicationContext,
            SalvarBanco::class.java,
            "banco_uber"
        ).fallbackToDestructiveMigration() // Isso ajuda se você mudou a tabela
            .build()

        setContentView(R.layout.activity_resultado)

        btnEtanolGasolina = findViewById(R.id.CacularEtanolGasolina)

        btnEtanolGasolina.setOnClickListener {
            val intent = Intent(this, EtanolGasolinaActivity::class.java)
            startActivity(intent)
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val txtLucro = findViewById<TextView>(R.id.txtLucro)
        val txtHora = findViewById<TextView>(R.id.txtHora)

        val lucro = intent.getDoubleExtra("lucro", 0.0)
        val ganhoHora = intent.getDoubleExtra("ganhoHora", 0.0)

        txtLucro.text = "Lucro: R$ %.2f".format(lucro)
        txtHora.text = "Por hora: R$ %.2f".format(ganhoHora)

        if (lucro >= 0) {
            txtLucro.setTextColor(android.graphics.Color.GREEN)
        } else {
            txtLucro.setTextColor(android.graphics.Color.RED)
        }
        val buttonSalvar = findViewById<Button>(R.id.bntSalvar)

        buttonSalvar.setOnClickListener {
            lifecycleScope.launch {
                try {
                    // 1. Cria o objeto para salvar
                    val registro = SalvarDia(
                        data = " SimpleDateFormat", // Ideal usar uma função de data atual
                        lucro = lucro,
                        porHora = ganhoHora,
                        combustivel = 0.0
                    )

                    // 2. Salva no banco (Aqui você usa o nome da variável do seu banco)
                    banco.salvarDao().salvarDia(registro)

                    // MENSAGEM DE SUCESSO
                    Toast.makeText(this@ResultadoActivity, "Salvo com sucesso!", Toast.LENGTH_SHORT).show()

                    // 3. O COMANDO QUE ESTÁ FALTANDO: ABRIR A TELA DE RELATÓRIO
                    val intent = Intent(this@ResultadoActivity, SalvarActivity::class.java)
                    startActivity(intent)

                    // Finaliza a tela atual para o usuário não voltar e salvar de novo
                    finish()

                } catch (e: Exception) {
                    Toast.makeText(this@ResultadoActivity, "Erro ao salvar!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }


}
