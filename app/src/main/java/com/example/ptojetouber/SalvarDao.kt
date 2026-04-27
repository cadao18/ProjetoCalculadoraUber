package com.example.ptojetouber

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SalvarDao {

    @Insert
    suspend fun salvarDia(salvarDia: SalvarDia)

    @Query("SELECT * FROM salvar_dia")
    suspend fun listarDias(): List<SalvarDia>
}