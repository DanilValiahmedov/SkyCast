package com.valimade.skycast.weather.ui.model.state

data class AddScreenState (
    val isLoading: Boolean = false,
    val namePlaces: List<String> = emptyList(),
    val isError: Boolean = false,
    val errorMessage: String = "Произошла ошибка",
)