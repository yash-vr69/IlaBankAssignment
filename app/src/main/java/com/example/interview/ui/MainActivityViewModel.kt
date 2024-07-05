package com.example.interview.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.interview.R
import com.example.interview.data.BannerContent
import com.example.interview.utils.Utils

class MainActivityViewModel : ViewModel() {

    private val _images: MutableLiveData<List<Int>> = MutableLiveData(
        listOf(
            R.drawable.fruits,
            R.drawable.vegetables,
            R.drawable.flowers
        )
    )

    private val _bannerList: MutableLiveData<ArrayList<BannerContent>> =
        MutableLiveData(Utils.fruitsList)

    val images: LiveData<List<Int>>
        get() = _images

    val bannerList: LiveData<ArrayList<BannerContent>>
        get() = _bannerList

    fun getRecyclerViewList(position: Int) {
        when (position) {
            0 -> _bannerList.value = Utils.fruitsList
            1 -> _bannerList.value = Utils.vegetableList
            2 -> _bannerList.value = Utils.flowerList
        }
    }
}