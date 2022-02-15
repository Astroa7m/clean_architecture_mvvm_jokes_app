package com.example.jokesapp.domain.use_case

import com.example.jokesapp.core.Resource
import com.example.jokesapp.data.remote.dto.Flags
import com.example.jokesapp.domain.model.PostJoke
import com.example.jokesapp.domain.repository.JokesRepository
import javax.inject.Inject

class AddJokeUseCase @Inject constructor(
    val repo: JokesRepository
) {
    suspend operator fun invoke(
        category: String,
        delivery: String? = null,
        flagsList: List<Boolean>,
        lang: String,
        joke: String? = null,
        setup: String? = null,
        type: String
    ) : Resource<Unit> {

        val flags = Flags(
            flagsList[0],
            flagsList[1],
            flagsList[2],
            flagsList[3],
            flagsList[4],
            flagsList[5],
        )

        val postJoke = if(type == "single"){
            PostJoke.PostJokeSingle(
                category,
                flags,
                lang,
                joke!!,
                type
            )
        }else{
            PostJoke.PostJokeTwoPart(
                category,
                delivery!!,
                flags,
                lang,
                setup!!,
                type
            )
        }


        return repo.addJoke(postJoke)
    }
}