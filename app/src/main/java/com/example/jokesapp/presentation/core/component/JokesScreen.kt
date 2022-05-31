package com.example.jokesapp.presentation.core.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.example.jokesapp.presentation.core.BaseJokesViewModel
import com.example.jokesapp.presentation.core.UIEvent
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapOffsets
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun JokesScreen(label: String, icon: Int, viewModel: BaseJokesViewModel) {
    val items = viewModel.state.value
    val lazyListState = rememberLazyListState()
    val scaffoldState = rememberScaffoldState()
    val firstVisibleItemIndex = remember {
        mutableStateOf(lazyListState.firstVisibleItemIndex)
    }

    val reloadModifier = Modifier.size(
        ((LocalConfiguration.current.screenHeightDp) / 4).dp,
        LocalConfiguration.current.screenWidthDp.dp
    )

    var isSearching by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            if (!isSearching) {
                DefaultAppBar(
                    label,
                    { isSearching = true }, {
                        viewModel.onFiltered(it)
                        viewModel.getAllJokes(isFiltering = true)
                    },
                    icon = icon
                )
            } else {
                SearchAppBar(
                    queryText = viewModel.searchQuery.value,
                    onQueryTextChange = viewModel::onSearchQueryTextChanged,
                    onSearchIconClicked = { viewModel.getAllJokes(true) }) {
                    isSearching = false
                    if (viewModel.searchQuery.value.isNotBlank()) {
                        viewModel.onDoneSearching()
                        viewModel.getAllJokes(isFiltering = true)
                    }
                }
            }
        },
        scaffoldState = scaffoldState
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            LazyRow(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                state = lazyListState,
                 flingBehavior = rememberSnapperFlingBehavior(
                     lazyListState,
                     snapOffsetForItem = SnapOffsets.Start,
                     snapIndex = { _, startIndex, targetIndex ->
                         targetIndex.coerceIn(startIndex - 1, startIndex + 1)
                     }
                 )
            ) {

                items(
                    items.jokes
                ) { item ->
                    JokeListItem(
                        joke = item, viewModel::onFavouriteItemClicked, modifier = Modifier.size(
                            LocalConfiguration.current.screenWidthDp.dp
                        )
                    )
                }

                //Retry button to request more if there is no error but item are the same
                item {
                    if (items.jokes.isNotEmpty() && !items.isLoading && items.errorMessage.isBlank()) {
                        RetryIconButton(reloadModifier, viewModel::getAllJokes)
                    }
                }

                //End Progress indicator
                item {
                    if (items.jokes.isNotEmpty() && items.isLoading) {
                        Box(
                            modifier = reloadModifier,
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

            }

            //Middle Progress indicator
            if (items.isLoading && items.jokes.isEmpty()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(8.dp)
                )
            }

            if (lazyListState.shouldLoadMore(firstVisibleItemIndex)) {
                viewModel.getAllJokes()
            }

            val lifecycleOwner = LocalLifecycleOwner.current
            LaunchedEffect(key1 = true) {
                viewModel.event.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                    .collectLatest { event ->
                        when (event) {
                            is UIEvent.SnackBar -> {
                                val snackBarResult =
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        event.message,
                                        "RETRY",
                                        SnackbarDuration.Long
                                    )
                                if (snackBarResult == SnackbarResult.ActionPerformed)
                                    viewModel.getAllJokes()
                            }
                        }
                    }
            }
        }
    }
}

@Composable
fun RetryIconButton(
    modifier: Modifier,
    onRetryClicked: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        IconButton(
            onClick = onRetryClicked
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Load more data"
            )
        }
    }
}

fun LazyListState.shouldLoadMore(rememberedIndex: MutableState<Int>): Boolean {
    val firstVisibleIndex = this.firstVisibleItemIndex
    if (rememberedIndex.value != firstVisibleIndex) {
        rememberedIndex.value = firstVisibleIndex
        return layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
    }
    return false
}

