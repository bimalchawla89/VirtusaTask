package com.example.task.ui.facts

import com.example.task.R

const val home="Home"

enum class BottomNavData(val route:String,val iconId:Int,val title:String) {
    Home("home", R.drawable.ic_baseline_home_24,home),
}