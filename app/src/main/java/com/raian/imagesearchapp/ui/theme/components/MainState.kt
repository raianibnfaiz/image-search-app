package com.raian.imagesearchapp.ui.theme.components

import com.raian.imagesearchapp.ui.theme.network.models.Hit

data class MainState(
    val isLoading: Boolean = false,
    val data: List<Hit> = emptyList(),
    val error: String= ""
)
