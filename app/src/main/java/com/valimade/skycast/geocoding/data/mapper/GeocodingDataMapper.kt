package com.valimade.skycast.geocoding.data.mapper

import com.valimade.skycast.geocoding.data.model.GeocodingPropertiesData
import com.valimade.skycast.geocoding.domain.model.GeocodingProperties

object GeocodingDataMapper {

    fun propertiesDataToDomain(properties: GeocodingPropertiesData): GeocodingProperties {
        return GeocodingProperties(
            country = properties.country,
            state = properties.state,
            city = properties.city,
        )
    }

}