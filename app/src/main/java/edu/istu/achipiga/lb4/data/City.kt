package edu.istu.achipiga.lb4.data

import androidx.annotation.StringRes

data class City(
    val id: Int,
    @StringRes val nameResId: Int,
    @StringRes val descriptionResId: Int,
)
