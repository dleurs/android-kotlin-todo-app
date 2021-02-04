package com.example.android.roomwordssample.mocki

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * This data class defines a Mars property which includes an ID, the image URL, the type (sale
 * or rental) and the price (monthly if it's a rental).
 * The property names of this data class are used by Moshi to match the names of values in JSON.
 */
@Parcelize
data class TodoApi (
    val id: String,
    val title: String,
    val completed: Boolean) : Parcelable

