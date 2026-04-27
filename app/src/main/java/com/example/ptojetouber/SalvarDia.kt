package com.example.ptojetouber

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "salvar_dia")
data class SalvarDia(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val data: String,
    val lucro: Double,
    val porHora: Double,
    val combustivel: Double
)