package com.dicoding.motive.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class DetailTvResponse(
        @SerializedName("id")
        var id: Int,
        @SerializedName("poster_path")
        var posterPath: String,
        @SerializedName("original_name")
        var originalName: String,
        @SerializedName("first_air_date")
        var firstAirDate: String,
        @SerializedName("overview")
        var overview: String
)
