package com.example.pry_mensual_eq6.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable

data class dataCursos(
    @SerialName("id") val id: Int = 0,
    @SerialName("carrera_id") val carreraid: Int = 0,
    @SerialName("curso") val curso: String = "",
    @SerialName("codigo") val codigo: String = "",
    @SerialName("descripción") val descripcion: String = "Sin descripción",
    @SerialName("espacio") val espacio: String = "",
    @SerialName("total_horas") val total_horas: String = "",
    @SerialName("dias_atencion") val dias_atencion: String = "",
    @SerialName("url_imagen_principal") val urlimagenprincipal: String = ""
)
