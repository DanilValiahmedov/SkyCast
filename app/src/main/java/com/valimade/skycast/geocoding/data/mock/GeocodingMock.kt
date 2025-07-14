package com.valimade.skycast.geocoding.data.mock

import com.valimade.skycast.geocoding.data.model.GeocodingFeaturesData
import com.valimade.skycast.geocoding.data.model.GeocodingPropertiesData
import com.valimade.skycast.geocoding.data.model.GeocodingReverseData

object GeocodingMock {

    val responseReverse = GeocodingReverseData(
        features = listOf(
            GeocodingFeaturesData(
                properties = GeocodingPropertiesData(
                    country = "Россия",
                    state = "Ленинградская область",
                    city = "Гатчина",
                )
            ),
            GeocodingFeaturesData(
                properties = GeocodingPropertiesData(
                    country = "Россия",
                    state = "Ленинградская область",
                    city = "Пушкин",
                )
            ),
            GeocodingFeaturesData(
                properties = GeocodingPropertiesData(
                    country = "Россия",
                    state = "Санкт-Петербург",
                    city = "Санкт-Петербург",
                )
            ),
        )
    )

}