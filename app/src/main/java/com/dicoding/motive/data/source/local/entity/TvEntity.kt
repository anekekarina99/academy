package com.dicoding.motive.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tb_tvfav")
@Parcelize
data class TvEntity(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name="id")
        var id: Int,

        @ColumnInfo(name="poster_path")
        var posterPath: String,

        @ColumnInfo(name="original_name")
        var originalName: String,

        @ColumnInfo(name="first_air_date")
        var firstAirDate: String,

        @ColumnInfo(name="overview")
        var overview: String,

        @NonNull
        @ColumnInfo(name = "isFavorite")
        var isFavorite: Boolean = false
):Parcelable
