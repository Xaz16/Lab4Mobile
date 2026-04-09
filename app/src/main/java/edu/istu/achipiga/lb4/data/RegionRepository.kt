package edu.istu.achipiga.lb4.data

import edu.istu.achipiga.lb4.R

object RegionRepository {

    private val cities = listOf(
        City(1, R.string.city_spb_name, R.string.city_spb_desc),
        City(2, R.string.city_vyborg_name, R.string.city_vyborg_desc),
        City(3, R.string.city_gatchina_name, R.string.city_gatchina_desc),
        City(4, R.string.city_pushkin_name, R.string.city_pushkin_desc),
        City(5, R.string.city_sosnovy_name, R.string.city_sosnovy_desc),
    )

    private val recommendations = listOf(
        Recommendation(101, 1, R.string.coffee_1_title, R.string.coffee_1_desc, R.drawable.bolshe_kofe),
        Recommendation(102, 1, R.string.coffee_2_title, R.string.coffee_2_desc, R.drawable.sibaristica),
        Recommendation(103, 1, R.string.coffee_3_title, R.string.coffee_3_desc, R.drawable.smena_coffee),
        Recommendation(104, 1, R.string.coffee_4_title, R.string.coffee_4_desc, R.drawable.aster_coffee),
        Recommendation(105, 1, R.string.coffee_5_title, R.string.coffee_5_desc, R.drawable.schegol_coffee),

        Recommendation(201, 2, R.string.rest_1_title, R.string.rest_1_desc, R.drawable.sea_signora),
        Recommendation(202, 2, R.string.rest_2_title, R.string.rest_2_desc, R.drawable.blok_restaurant),
        Recommendation(203, 2, R.string.rest_3_title, R.string.rest_3_desc, R.drawable.duo_asia),
        Recommendation(204, 2, R.string.rest_4_title, R.string.rest_4_desc, R.drawable.mansarda_restaurant),
        Recommendation(205, 2, R.string.rest_5_title, R.string.rest_5_desc, R.drawable.terrassa_restaurant),

        Recommendation(301, 3, R.string.kids_1_title, R.string.kids_1_desc, R.drawable.muzej_art_i_fakty),
        Recommendation(302, 3, R.string.kids_2_title, R.string.kids_2_desc, R.drawable.nebylica_moryanica),
        Recommendation(303, 3, R.string.kids_3_title, R.string.kids_3_desc, R.drawable.muzej_bestiary),
        Recommendation(304, 3, R.string.kids_4_title, R.string.kids_4_desc, R.drawable.fabrika_samoylovoy),
        Recommendation(305, 3, R.string.kids_5_title, R.string.kids_5_desc, R.drawable.galaktorium),

        Recommendation(401, 4, R.string.park_1_title, R.string.park_1_desc, R.drawable.letniy_sad_spb),
        Recommendation(402, 4, R.string.park_2_title, R.string.park_2_desc, R.drawable.mihaylovskiy_sad),
        Recommendation(403, 4, R.string.park_3_title, R.string.park_3_desc, R.drawable.tavricheskiy_sad),
        Recommendation(404, 4, R.string.park_4_title, R.string.park_4_desc, R.drawable.botanicheskiy_sad_petra),
        Recommendation(405, 4, R.string.park_5_title, R.string.park_5_desc, R.drawable.aleksandrovskiy_sad),

        Recommendation(501, 5, R.string.mall_1_title, R.string.mall_1_desc, R.drawable.tts_galeriya),
        Recommendation(502, 5, R.string.mall_2_title, R.string.mall_2_desc, R.drawable.mega_dybenko),
        Recommendation(503, 5, R.string.mall_3_title, R.string.mall_3_desc, R.drawable.zhemchuzhnaya_plaza),
        Recommendation(504, 5, R.string.mall_4_title, R.string.mall_4_desc, R.drawable.piterlend_trc),
        Recommendation(505, 5, R.string.mall_5_title, R.string.mall_5_desc, R.drawable.trk_raduga),
    )

    fun getCities(): List<City> = cities

    fun getCity(cityId: Int): City? = cities.find { it.id == cityId }

    fun getRecommendationsForCategory(categoryId: Int): List<Recommendation> =
        recommendations.filter { it.categoryId == categoryId }

    fun getRecommendation(recommendationId: Int): Recommendation? =
        recommendations.find { it.id == recommendationId }
}
