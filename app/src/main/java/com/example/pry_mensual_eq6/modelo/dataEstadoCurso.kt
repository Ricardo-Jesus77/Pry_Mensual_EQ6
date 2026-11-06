package com.example.pry_mensual_eq6.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataEstadoCurso(
    @SerialName("id") val id: Int = 0,
    @SerialName("carrera_id") val carreraid: Int = 0,
    @SerialName("curso") val curso: String = "",
    @SerialName("url_imagen_principal") val urlimagenprincipal: String = "",
    @SerialName("cupo") val cupo: Int = 0,
    @SerialName("inscritos") val inscritos: Int = 0
)