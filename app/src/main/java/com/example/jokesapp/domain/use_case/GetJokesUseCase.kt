package com.example.jokesapp.domain.use_case

import com.example.jokesapp.core.Resource
import com.example.jokesapp.domain.model.Joke
import com.example.jokesapp.domain.repository.JokesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

class GetJokesUseCase @Inject constructor(
    private val repo: JokesRepository
) {

    operator fun invoke(
        category: List<String>,
        queryString: String,
        jokesPreference: List<Boolean>,
        count: Int,
        blacklistFlags: List<String>,
    ): Flow<Resource<List<Joke>>> {
        val type = when{
            jokesPreference[1] && !jokesPreference[0] -> "twopart"
            jokesPreference[0] && !jokesPreference[1] -> "single"
            else -> ""
        }


        val firstTen = repo.getAllJokes(
            category,
            queryString,
            type,
            count,
            blacklistFlags,
            jokesPreference[2]
        )


        val secondTen = repo.getAllJokes(
            category,
            queryString,
            type,
            count,
            blacklistFlags,
            jokesPreference[2]
        )

        return merge(firstTen, secondTen)
    }

}