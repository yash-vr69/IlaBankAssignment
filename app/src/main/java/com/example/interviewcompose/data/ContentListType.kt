package com.example.interviewcompose.data

import com.example.interviewcompose.R
import com.example.interviewcompose.utils.Utils

sealed class ContentListType(val position: Int,val drawable: Int,val list: List<BannerContent>){
    object Fruits: ContentListType(0, R.drawable.fruits, Utils.fruitsList)
    object Vegetables: ContentListType(1,R.drawable.vegetables, Utils.vegetableList)
    object Flowers: ContentListType(2,R.drawable.flowers, Utils.flowerList)

    companion object{
        fun toList() = listOf(Fruits,Vegetables,Flowers)
    }
}
