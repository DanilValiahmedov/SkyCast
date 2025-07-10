package com.valimade.skycast.geocoding.ui.mapper

import com.valimade.skycast.geocoding.domain.model.GeocodingFeatures
import com.valimade.skycast.geocoding.domain.model.GeocodingProperties
import com.valimade.skycast.geocoding.domain.model.GeocodingReverse
import com.valimade.skycast.geocoding.ui.model.GeocodingFeaturesUI
import com.valimade.skycast.geocoding.ui.model.GeocodingPropertiesUI
import com.valimade.skycast.geocoding.ui.model.GeocodingReverseUI

object GeocodingUIMapper {

    fun reverseDomainToUI(reverse: GeocodingReverse): GeocodingReverseUI {
        return GeocodingReverseUI(
            features = reverse.features.map {
                featuresDomainToUI(it)
            },
        )
    }

    private fun featuresDomainToUI(features: GeocodingFeatures): GeocodingFeaturesUI {
        return GeocodingFeaturesUI(
            properties = propertiesDomainToUI(features.properties),
        )
    }

    private fun propertiesDomainToUI(properties: GeocodingProperties): GeocodingPropertiesUI {
        return GeocodingPropertiesUI(
            country = properties.country,
            state = properties.state,
            city = properties.city,
        )
    }

}