package com.example.a7daysapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Day(
    val day : String,
    @StringRes val activity : Int,
    @DrawableRes val image : Int,
    @StringRes val description : Int
)