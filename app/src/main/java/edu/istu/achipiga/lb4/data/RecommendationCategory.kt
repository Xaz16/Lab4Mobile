package edu.istu.achipiga.lb4.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import edu.istu.achipiga.lb4.R

enum class RecommendationCategory(
    val id: Int,
    @StringRes val titleResId: Int,
    @DrawableRes val iconResId: Int,
) {
    Coffee(1, R.string.category_coffee, R.drawable.ic_cat_coffee),
    Restaurants(2, R.string.category_restaurants, R.drawable.ic_cat_restaurant),
    Kids(3, R.string.category_kids, R.drawable.ic_cat_kids),
    Parks(4, R.string.category_parks, R.drawable.ic_cat_park),
    Malls(5, R.string.category_malls, R.drawable.ic_cat_mall),
    ;

    companion object {
        fun fromId(id: Int): RecommendationCategory? =
            RecommendationCategory.values().firstOrNull { it.id == id }
    }
}
