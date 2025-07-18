package com.valimade.skycast.geocoding.ui.mapper

import com.valimade.skycast.geocoding.domain.model.GeocodingProperties

object GeocodingUIMapper {

    fun propertiesDomainToUI(properties: GeocodingProperties): String {
        return "${properties.city} (${properties.state}, ${properties.country})"
    }

}
