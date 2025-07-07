package com.valimade.skycast.geocoding.data.mapper

import com.valimade.skycast.geocoding.data.model.GeocodingFeaturesData
import com.valimade.skycast.geocoding.data.model.GeocodingPropertiesData
import com.valimade.skycast.geocoding.data.model.GeocodingReverseData
import com.valimade.skycast.geocoding.domain.model.GeocodingFeatures
import com.valimade.skycast.geocoding.domain.model.GeocodingProperties
import com.valimade.skycast.geocoding.domain.model.GeocodingReverse

object GeocodingDataMapper {

    fun reverseDataToDomain(reverse: GeocodingReverseData): GeocodingReverse {
        return GeocodingReverse(
            features = reverse.features.map {
                featuresDataToDomain(it)
            },
        )
    }

    fun reverseDomainToData(reverse: GeocodingReverse): GeocodingReverseData {
        return GeocodingReverseData(
            features = reverse.features.map {
                featuresDomainToData(it)
            },
        )
    }

    private fun featuresDataToDomain(features: GeocodingFeaturesData): GeocodingFeatures {
        return GeocodingFeatures(
            properties = propertiesDataToDomain(features.properties),
        )
    }
    private fun featuresDomainToData(features: GeocodingFeatures): GeocodingFeaturesData {
        return GeocodingFeaturesData(
            properties = propertiesDomainToData(features.properties),
        )
    }

    private fun propertiesDataToDomain(properties: GeocodingPropertiesData): GeocodingProperties {
        return GeocodingProperties(
            country = properties.country,
            state = properties.state,
            city = properties.city,
        )
    }
    private fun propertiesDomainToData(properties: GeocodingProperties): GeocodingPropertiesData {
        return GeocodingPropertiesData(
            country = properties.country,
            state = properties.state,
            city = properties.city,
        )
    }

}