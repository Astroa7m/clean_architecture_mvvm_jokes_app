package com.astroscoding.jokesapp.di

import android.app.Application
import androidx.room.Room
import com.astroscoding.jokesapp.data.local.JokesDao
import com.astroscoding.jokesapp.data.local.JokesDatabase
import com.astroscoding.jokesapp.data.local.PreferencesManager
import com.astroscoding.jokesapp.data.remote.JokesRemoteSource
import com.astroscoding.jokesapp.data.remote.JokesRemoteSourceImpl
import com.astroscoding.jokesapp.data.repository.JokesRepositoryImpl
import com.astroscoding.jokesapp.domain.repository.JokesRepository
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
    fun providePreferencesManager(application: Application) = PreferencesManager(application)

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
                    prettyPrint = true
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