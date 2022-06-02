package com.astroscoding.jokesapp.data.local

import androidx.room.*
import com.astroscoding.jokesapp.data.local.model.JokeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JokesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addJoke(jokes: JokeEntity)

    @Delete
    suspend fun deleteJoke(jokes: JokeEntity)

    @Query("SELECT * FROM jokes_table")
    fun getAllJokes(): Flow<List<JokeEntity>>

    @Query("DELETE FROM jokes_table")
    suspend fun deleteAllJokes()

    @Query("SELECT * FROM jokes_table WHERE id = :id")
    suspend fun findItem(id: Int) : JokeEntity?

}