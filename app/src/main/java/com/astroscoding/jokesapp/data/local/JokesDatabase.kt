package com.astroscoding.jokesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.astroscoding.jokesapp.data.local.model.JokeEntity

@Database(
    entities = [JokeEntity::class],
    version = 1,
    exportSchema = false
)
abstract class JokesDatabase : RoomDatabase() {

    abstract fun getJokesDao(): JokesDao

}