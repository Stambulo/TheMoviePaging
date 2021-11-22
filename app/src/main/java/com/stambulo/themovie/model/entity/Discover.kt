package com.stambulo.themovie.model.entity

data class Discover(
    val page: Int,
    var results: List<VideoItem>,
    val total_pages: Int
)
