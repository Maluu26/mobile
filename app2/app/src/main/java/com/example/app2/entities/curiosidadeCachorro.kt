package com.example.app2.entities

import com.google.gson.annotations.SerializedName

class curiosidadeCachorro {
    @SerializedName("facts")
    val facts: List<String> = emptyList()
    @SerializedName("success")
    var sucess: Boolean = false
}