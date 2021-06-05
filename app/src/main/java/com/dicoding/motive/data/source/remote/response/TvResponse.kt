package com.dicoding.motive.data.source.remote.response


import android.os.Parcelable
import com.dicoding.motive.data.source.local.entity.MovieEntity
import com.dicoding.motive.data.source.local.entity.TvEntity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.ArrayList


data class TvResponse(
        var results : ArrayList<DetailTvResponse>
)

