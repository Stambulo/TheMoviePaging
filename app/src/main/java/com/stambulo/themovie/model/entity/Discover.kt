package com.stambulo.themovie.model.entity

data class Discover(
    val page: Int,
    val results: List<VideoItem>,
    val total_pages: Int
)
