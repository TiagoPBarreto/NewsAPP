package com.barreto.newsapp.presentation.nvgraph

sealed class Route(
    val route: String
){
    object OnBoardingScreen : Route(route = "onBorardingScreen")
    object HomeScreen : Route(route = "homeScreen")
    object SearchScreen : Route(route = "searchScreen")
    object BookMarkScreen : Route(route = "bookMarkScreen")
    object DetailsScreen : Route(route = "detailsScreen")
    object AppStartNavigation : Route(route = "appStarNavigation")
    object NewsNavigation : Route(route = "newsNavigation")
    object NewsNavigationScreen : Route(route = "newsNavigationScreen")

}
