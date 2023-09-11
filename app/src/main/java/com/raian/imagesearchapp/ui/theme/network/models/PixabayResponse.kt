package com.raian.imagesearchapp.ui.theme.network.models

data class PixabayResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)