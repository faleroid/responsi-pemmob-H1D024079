package com.pemmob.responsipemmob.data.model

import com.google.gson.annotations.SerializedName

data class GempaResponse(
    @SerializedName("Infogempa")
    val infoGempa: InfoGempa? = null
)
data class InfoGempa(
    @SerializedName("gempa")
    val gempa: List<Gempa>? = null
)
data class Gempa(
    @SerializedName("Tanggal") val tanggal: String? = null,
    @SerializedName("Jam") val jam: String? = null,
    @SerializedName("Coordinates") val coordinates: String? = null,
    @SerializedName("Magnitude") val magnitude: String? = null,
    @SerializedName("Kedalaman") val kedalaman: String? = null,
    @SerializedName("Wilayah") val wilayah: String? = null,
    @SerializedName("Potensi") val potensi: String? = null
)

fun Gempa.magnitudeAngka(): Double = magnitude?.toDoubleOrNull() ?: 0.0