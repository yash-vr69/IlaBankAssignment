package com.example.interviewcompose.utils

import com.example.interviewcompose.R
import com.example.interviewcompose.data.BannerContent

object Utils {

    val fruitsList: ArrayList<BannerContent> = ArrayList()
    val vegetableList: ArrayList<BannerContent> = ArrayList()
    val flowerList: ArrayList<BannerContent> = ArrayList()

    init {
        val bannerListScreen1 = listOf(
            BannerContent(R.drawable.fruits,"Fruit","Apple"),
            BannerContent(R.drawable.fruits,"Fruit","Orange"),
            BannerContent(R.drawable.fruits,"Fruit","PineApple"),
            BannerContent(R.drawable.fruits,"Fruit","Grapes"),
            BannerContent(R.drawable.fruits,"Fruit","Blueberry"),
            BannerContent(R.drawable.fruits,"Fruit","Banana"),
            BannerContent(R.drawable.fruits,"Fruit","Fig"),
            BannerContent(R.drawable.fruits,"Fruit","Apricot"))

        val bannerListScreen2 = listOf(
            BannerContent(R.drawable.vegetables,"Vegetable","Tomato"),
            BannerContent(R.drawable.vegetables,"Vegetable","Bottle Gourd"),
            BannerContent(R.drawable.vegetables,"Vegetable","Potato"),
            BannerContent(R.drawable.vegetables,"Vegetable","Pea"),
            BannerContent(R.drawable.vegetables,"Vegetable","Green Chili"),
            BannerContent(R.drawable.vegetables,"Vegetable","Cucumber"),
            BannerContent(R.drawable.vegetables,"Vegetable","Onion"),
            BannerContent(R.drawable.vegetables,"Vegetable","Cauliflower")
        )
        val bannerListScreen3 = listOf(
            BannerContent(R.drawable.flowers,"Flower","Lily"),
            BannerContent(R.drawable.flowers,"Flower","Daisy"),
            BannerContent(R.drawable.flowers,"Flower","Marigold"),
            BannerContent(R.drawable.flowers,"Flower","Rose"),
            BannerContent(R.drawable.flowers,"Flower","Hibiscus"),
            BannerContent(R.drawable.flowers,"Flower","Orchid"),
            BannerContent(R.drawable.flowers,"Flower","Iris"),
            BannerContent(R.drawable.flowers,"Flower","Jasmine"),
            BannerContent(R.drawable.flowers,"Flower","Lavender"),
            BannerContent(R.drawable.flowers,"Flower","Sunflower")
        )
        fruitsList.addAll(bannerListScreen1)
        vegetableList.addAll(bannerListScreen2)
        flowerList.addAll(bannerListScreen3)

    }
}