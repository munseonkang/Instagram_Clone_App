package com.cookandroid.instagram_android_moon.config

import com.google.gson.annotations.SerializedName

open class BaseResponse (
    @SerializedName("isSuccess") val isSuccess: Boolean = false,
    @SerializedName("code") val code: Int = 0,
    @SerializedName("message") val message: String? = null
)