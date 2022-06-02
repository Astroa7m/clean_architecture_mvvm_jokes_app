package com.astroscoding.jokesapp.domain.use_case

import com.astroscoding.jokesapp.core.Resource
import com.astroscoding.jokesapp.data.remote.dto.Flags
import com.astroscoding.jokesapp.domain.model.PostJoke
import com.astroscoding.jokesapp.domain.repository.JokesRepository
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