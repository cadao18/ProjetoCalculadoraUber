package com.example.ptojetouber

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SalvarDia::class],
    version = 1
)
abstract class SalvarBanco : RoomDatabase() {

    abstract fun salvarDao(): SalvarDao
}