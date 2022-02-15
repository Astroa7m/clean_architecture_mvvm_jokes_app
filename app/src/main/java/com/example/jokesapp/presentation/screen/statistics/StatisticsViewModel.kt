package com.example.jokesapp.presentation.screen.statistics

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.domain.use_case.GetFavoriteJokesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val getFavoriteJokesUseCase: GetFavoriteJokesUseCase
) : ViewModel() {

    private val _statisticsText = mutableStateOf("")
    val statisticsText: State<String> = _statisticsText

    init {
        getAllFavouriteAndAnalyze()
    }

    private fun getAllFavouriteAndAnalyze() = viewModelScope.launch {
        var punCategory = 0
        var programmingCategory = 0
        var miscCategory = 0
        var christmasCategory = 0
        var darkCategory = 0
        var spookyCategory = 0
        val categoryStatisticsList = mutableListOf<String>()
        getFavoriteJokesUseCase().collectLatest { jokes ->
            val size = jokes.size
            jokes.forEach {
                if (it.category == "Pun") {
                    punCategory++
                }
                if (it.category == "Programming") {
                    programmingCategory++
                }
                if (it.category == "Misc") {
                    miscCategory++
                }
                if (it.category == "Christmas") {
                    christmasCategory++
                }
                if (it.category == "Dark") {
                    darkCategory++
                }
                if (it.category == "Spooky") {
                    spookyCategory++
                }
            }
            if (punCategory != 0)
                categoryStatisticsList.add(getAverageText(punCategory, "pun", size))
            if (programmingCategory != 0)
                categoryStatisticsList.add(getAverageText(programmingCategory, "programming", size))
            if (miscCategory != 0)
                categoryStatisticsList.add(getAverageText(miscCategory, "misc", size))
            if (christmasCategory != 0)
                categoryStatisticsList.add(getAverageText(christmasCategory, "christmas", size))
            if (darkCategory != 0)
                categoryStatisticsList.add(getAverageText(darkCategory, "dark", size))
            if (spookyCategory != 0)
                categoryStatisticsList.add(getAverageText(spookyCategory, "spooky", size))
            _statisticsText.value = categoryStatisticsList.joinToString().replace(",", "\n")
        }
    }

    private fun getAverageText(
        categoryInt: Int,
        categoryString: String,
        listSize: Int
    ) : String {
        val average = "%.1f".format(((categoryInt.toFloat() / listSize.toFloat()) * 100f))
        return "$categoryInt $categoryString ${if (categoryInt == 1) "joke" else "jokes"} with average $average%"
    }
}