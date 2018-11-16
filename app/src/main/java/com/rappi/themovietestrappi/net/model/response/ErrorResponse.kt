package com.rappi.themovietestrappi.net.model.response

import com.google.gson.annotations.SerializedName

class ErrorResponse(
    @SerializedName("status_code", alternate = ["code"]) val statusCode: Int,
    @SerializedName("status_message", alternate = ["message"]) val statusMessage: String
)