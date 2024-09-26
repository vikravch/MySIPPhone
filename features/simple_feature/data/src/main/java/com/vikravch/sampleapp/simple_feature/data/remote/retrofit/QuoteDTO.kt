package com.vikravch.sampleapp.simple_feature.data.remote.retrofit

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class QuoteDTO {
    @SerializedName("quote")
    val quote: String = ""
}