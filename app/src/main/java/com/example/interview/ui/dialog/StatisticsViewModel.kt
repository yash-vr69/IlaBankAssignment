package com.example.interview.ui.dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.interview.data.BannerContent

class StatisticsViewModel: ViewModel() {

    private var _statisticsList: MutableLiveData<List<String>> = MutableLiveData(
        listOf()
    )

    val statsList: LiveData<List<String>>
        get() = _statisticsList


    fun getStatistics(data: List<BannerContent>) {
        val charCount = mutableMapOf<Char, Int>()
        data.forEach { item ->
            item.body.forEach { char ->
                charCount[char.lowercaseChar()] = charCount.getOrDefault(char.lowercaseChar(), 0) + 1
            }
        }
        _statisticsList.value =  charCount.entries.sortedByDescending { it.value }.take(3)
            .map { "${it.key} = ${it.value}" }
    }

}