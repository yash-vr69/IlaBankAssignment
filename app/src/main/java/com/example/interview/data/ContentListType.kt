package com.example.interview.data

import com.example.interview.R
import com.example.interview.utils.Utils

sealed class ContentListType(val position: Int, val drawable: Int, val list: List<BannerContent>) {
    object Fruits : ContentListType(0, R.drawable.fruits, Utils.fruitsList)
    object Vegetables : ContentListType(1, R.drawable.vegetables, Utils.vegetableList)
    object Flowers : ContentListType(2, R.drawable.flowers, Utils.flowerList)
}
