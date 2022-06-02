package com.astroscoding.jokesapp.domain.use_case

import com.astroscoding.jokesapp.core.Resource
import com.astroscoding.jokesapp.domain.model.Joke
import com.astroscoding.jokesapp.domain.repository.JokesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge
import javax.inject.Inject

class GetJokesUseCase @Inject constructor(
    private val repo: JokesRepository
) {

    operator fun invoke(
        category: List<String>,
        queryString: String,
        jokesPreference: Pair<Boolean, Boolean>,
        count: Int,
        blacklistFlags: List<String>,
    ): Flow<Resource<List<Joke>>> {
        val type = when{
            jokesPreference.second && !jokesPreference.first -> "twopart"
            jokesPreference.first && !jokesPreference.second -> "single"
            else -> ""
        }


        val firstTen = repo.getAllJokes(
            category,
            queryString,
            type,
            count,
            blacklistFlags
        )


        val secondTen = repo.getAllJokes(
            category,
            queryString,
            type,
            count,
            blacklistFlags
        )

        return merge(firstTen, secondTen)
    }

}