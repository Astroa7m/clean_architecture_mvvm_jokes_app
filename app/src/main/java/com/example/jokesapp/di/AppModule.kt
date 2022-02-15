package com.example.jokesapp.di

import android.app.Application
import androidx.room.Room
import com.example.jokesapp.data.local.JokesDao
import com.example.jokesapp.data.local.JokesDatabase
import com.example.jokesapp.data.remote.JokesRemoteSource
import com.example.jokesapp.data.remote.JokesRemoteSourceImpl
import com.example.jokesapp.data.repository.JokesRepositoryImpl
import com.example.jokesapp.domain.repository.JokesRepository
import com.example.jokesapp.domain.use_case.GetJokesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideJokesDao(context: Application) : JokesDao{
        return Room.databaseBuilder(
            context,
            JokesDatabase::class.java,
            "jokes_database"
        ).build().getJokesDao()
    }

    @Singleton
    @Provides
    fun provideRemoteDataSource() : JokesRemoteSource{
        val client = HttpClient(Android){
            install(Logging){
                level = LogLevel.ALL
            }
            install(JsonFeature){
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json{
                    ignoreUnknownKeys = true
                })
            }
        }

        return JokesRemoteSourceImpl(client)
    }

    @Singleton
    @Provides
    fun provideRepo(
        dao: JokesDao,
        remoteDataSource: JokesRemoteSource
    ) : JokesRepository{
        return JokesRepositoryImpl(
            dao,
            remoteDataSource
        )
    }
}