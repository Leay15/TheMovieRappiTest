package com.rappi.themovietestrappi.net

object DataConfiguration {
    const val BASE_IMAGES_URL = "http://image.tmdb.org/t/p/"
    const val API_KEY = "9c10167497178a91d6e39d04c5382674"
    const val API_ACCESS_TOKEN =
        "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5YzEwMTY3NDk3MTc4YTkxZDZlMzlkMDRjNTM4MjY3NCIsInN1YiI6IjViZWNlOTlmYzNhMzY4MTJmYTAwYjVkNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.C-iHGGSocnePMyplyZo1xdxl77Nj2zOl1W7sL8NahL4"

    const val POPULAR = "movie/popular"
    const val TOP_RATED = "movie/top_rated"
    const val UPCOMING = "movie/upcoming"
    const val VIDEOS = "movie/{movie_id}/videos"
    const val MOVIE_DETAIL = "movie/{movie_id}"
}