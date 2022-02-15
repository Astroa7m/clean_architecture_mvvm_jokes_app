package com.example.jokesapp.data.repository

import com.example.jokesapp.core.Resource
import com.example.jokesapp.data.local.JokesDao
import com.example.jokesapp.data.remote.JokesRemoteSource
import com.example.jokesapp.domain.model.Joke
import com.example.jokesapp.domain.model.PostJoke
import com.example.jokesapp.domain.repository.JokesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class JokesRepositoryImpl @Inject constructor(
    private val localDataSource: JokesDao,
    private val remoteDataSource: JokesRemoteSource
) : JokesRepository {

    override fun getAllJokes(
        category: List<String>,
        queryString: String,
        type: String,
        count: Int,
        blacklistFlags: List<String>,
        isSafeMode: Boolean
    ): Flow<Resource<List<Joke>>> = flow {
        try {
            emit(Resource.Loading())
            val categoryString = category.joinToString().replace(" ", "").ifBlank { "Any" }
            val blacklistFlagsString =
                blacklistFlags.joinToString().replace(" ", "").ifBlank { "" }

            val result = remoteDataSource.getJokes(
                categoryString,
                queryString,
                type,
                count,
                blacklistFlagsString,
                isSafeMode
            ).jokes

            emit(Resource.Success(result.map { it.toDomainJokes() }))
        } catch (e: IOException) {
            emit(Resource.Failure("Could not reach server, please check your internet connection"))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Failure("Error occurred while fetching remote data, try check the filters & query"))
        }

    }

    override fun getFavouriteJokes(): Flow<List<Joke>> {
        return localDataSource.getAllJokes().map { list ->
            list.map { item ->
                item.toJoke()
            }
        }
    }

    override suspend fun unFavouriteJoke(joke: Joke) {
        localDataSource.deleteJoke(joke.toJokeEntity())
    }

    override suspend fun unFavouriteAllJokes() {
        localDataSource.deleteAllJokes()
    }

    override suspend fun addJokesToFavourite(joke: Joke) {
        localDataSource.addJoke(joke.toJokeEntity())
    }

    override suspend fun addJoke(joke: PostJoke): Resource<Unit> {
        return try {
            if(joke is PostJoke.PostJokeSingle){
                remoteDataSource.addJoke(joke.toPostJokeSingleDto())
            }else if(joke is PostJoke.PostJokeTwoPart){
                remoteDataSource.addJoke(jokeTwoPart = joke.toPostJokeTwoPartDto())
            }
            Resource.Success(Unit)
        } catch (e: IOException) {
            Resource.Failure("Couldn't reach server please check your internet connection")
        }catch (e: Exception) {
            Resource.Failure(e.message ?: "Error Occurred while trying to add new joke")
        }
    }
}