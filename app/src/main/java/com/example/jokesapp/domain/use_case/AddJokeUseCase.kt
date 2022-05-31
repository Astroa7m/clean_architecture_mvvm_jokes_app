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
        lang: String,
        joke: String? = null,
        setup: String? = null,
        type: String
    ) : Resource<Unit> {


        val postJoke = if(type == "single"){
            PostJoke.PostJokeSingle(
                category,
                Flags(false, false, false, false, false, false),
                lang,
                joke!!,
                type
            )
        }else{
            PostJoke.PostJokeTwoPart(
                category,
                delivery!!,
                Flags(false, false, false, false, false, false),
                lang,
                setup!!,
                type
            )
        }


        return repo.addJoke(postJoke)
    }
}