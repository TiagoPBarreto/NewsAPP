package com.barreto.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.barreto.newsapp.R

data class Page(
    val title:String,
    val description:String,
    @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = " is simply dummy text of the printing and typesetting industry.",
        description = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical",
        image = R.drawable.onboarding1

    ),
    Page(
        title = " is simply dummy text of the printing and typesetting industry.",
        description = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical",
        image = R.drawable.onboarding2

    ),
    Page(
        title = " is simply dummy text of the printing and typesetting industry.",
        description = "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical",
        image = R.drawable.onboarding3

    )
)
