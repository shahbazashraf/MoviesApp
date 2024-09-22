package com.user.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MediaResponse(
    val page: Int,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val results: List<MediaItem>
):Parcelable

@Parcelize
data class MediaItem(
    val id: Int,
    @SerializedName("media_type")
    val mediaType: String,
    val title: String?,
    val name: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    val overview: String?,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("first_air_date")
    val firstAirDate: String?
):Parcelable
