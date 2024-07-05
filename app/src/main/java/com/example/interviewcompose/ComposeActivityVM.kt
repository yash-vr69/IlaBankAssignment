package com.example.interviewcompose

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.interviewcompose.data.ContentListType

class ComposeActivityVM: ViewModel() {

    private val tabs = ContentListType.toList()

    private val _selectedTab: MutableState<ContentListType> = mutableStateOf<ContentListType>(ContentListType.Fruits)
    val selectedTab: State<ContentListType> = _selectedTab

    private val _userInput = mutableStateOf<String>("")
    val userInput: State<String> = _userInput

    val currentlist by derivedStateOf {
        val key  = userInput.value

        selectedTab.value.list.filter {
            it.body.contains(key,true)
        }
    }

    fun updateSelectedTab(tabPosition: Int){
        _selectedTab.value = tabs[tabPosition]
        _userInput.value = ""
    }

    fun getTab(tabPosition: Int): ContentListType {
        return tabs[tabPosition]
    }

    fun setUserInput(input: String){
        _userInput.value = input
    }

    fun getStatistics(): List<String>? {
        val charCount = mutableMapOf<Char, Int>()
        currentlist.forEach { item ->
            item.body.forEach { char ->
                charCount[char.lowercaseChar()] = charCount.getOrDefault(char.lowercaseChar(), 0) + 1
            }
        }

        if(currentlist.isNotEmpty()) {
            return charCount.entries.sortedByDescending { it.value }.take(3)
                .map { "${it.key} = ${it.value}" }
        }else{
            return null
        }
    }

}