package com.valimade.skycast.geocoding.ui.mapper

import com.valimade.skycast.geocoding.domain.model.GeocodingProperties
import com.valimade.skycast.geocoding.ui.model.GeocodingPropertiesUI

object GeocodingUIMapper {

    fun propertiesDomainToUI(properties: GeocodingProperties): GeocodingPropertiesUI {
        return GeocodingPropertiesUI(
            country = properties.country,
            state = properties.state,
            city = properties.city,
        )
    }

}