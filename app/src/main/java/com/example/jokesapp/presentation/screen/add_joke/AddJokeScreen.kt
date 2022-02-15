package com.example.jokesapp.presentation.screen.add_joke

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.example.jokesapp.core.Resource
import com.example.jokesapp.presentation.core.component.DefaultAppBar
import com.example.jokesapp.presentation.screen.add_joke.comp.AddJokeMainScreen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddJokeScreen(viewModel: AddJokeViewModel = hiltViewModel()) {
    var isSingle by remember {
        mutableStateOf(true)
    }

    var showProgress by remember {
        mutableStateOf(false)
    }

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            DefaultAppBar(
                ScreenTitle = "Add Joke",
                isLocal = true,
                iconVector = Icons.Default.Add
            )
        },
        scaffoldState = scaffoldState
    ) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AddJokeMainScreen(
                viewModel,
                isSingle
            ){ isSingle = isSingle.not() }

            AnimatedVisibility(
                visible = showProgress,
                modifier = Modifier.align(Alignment.Center)
            ) {
                CircularProgressIndicator()
            }
        }

        val lifecycleOwner = LocalLifecycleOwner.current
        LaunchedEffect(key1 = true){
            viewModel.submissionResult.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collectLatest { result->
                    showProgress = when (result){
                        is Resource.Success ->{
                            scaffoldState.snackbarHostState.showSnackbar(
                                "Joke's been successfully submitted, it'll be added after reviewing it by the author"
                            )
                            false
                        }
                        is Resource.Failure -> {
                            scaffoldState.snackbarHostState.showSnackbar(
                                result.message ?: ""
                            )
                            false
                        }
                        is Resource.Loading -> true
                    }
                }
        }
    }

}

