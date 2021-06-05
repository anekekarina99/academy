package com.dicoding.motive.data.source.remote.response

import com.google.gson.annotations.SerializedName



data class DetailMovieResponse(
        @SerializedName("id")
        var id: Int,
        @SerializedName("poster_path")
        var posterPath : String,
        @SerializedName("original_title")
        var originalTitle: String,
        @SerializedName("release_date")
        var releaseDate: String,
        @SerializedName("overview")
        var overview: String,
)

