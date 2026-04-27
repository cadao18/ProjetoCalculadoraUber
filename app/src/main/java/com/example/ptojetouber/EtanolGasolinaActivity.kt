    package com.example.ptojetouber

    import android.icu.text.Edits
    import android.os.Bundle
    import android.widget.Button
    import android.widget.EditText
    import android.widget.TextView
    import androidx.activity.enableEdgeToEdge
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat
    import android.graphics.Color

    class EtanolGasolinaActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_etanol_gasolina)
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
            val bntEtanolGasolina = findViewById<Button>(R.id.bntEtanolGasolina)
            val editEtanol = findViewById<EditText>(R.id.editEtanol)
            val editgasolina = findViewById<EditText>(R.id.editGasolina)
            val textResultado = findViewById<TextView>(R.id.textResultado)



            bntEtanolGasolina.setOnClickListener {
                val etanol = editEtanol.text.toString().toDoubleOrNull()?:0.0
                val gasolina = editgasolina.text.toString().toDoubleOrNull()?:0.0

                if( etanol > 0 && gasolina > 0){
                    val resultado = etanol / gasolina

                    if(resultado <= 0.7){
                        textResultado.text = "ETANOL"
                        textResultado.setTextColor(Color.GREEN)
                    }else{
                        textResultado.text = "GASOLINA"
                        textResultado.setTextColor(Color.GREEN)
                    }

                }else{
                    textResultado.text = "Digite números válidos"
                }


            }


        }
    }