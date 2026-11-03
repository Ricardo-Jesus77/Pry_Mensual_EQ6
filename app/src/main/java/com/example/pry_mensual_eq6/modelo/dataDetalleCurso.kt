package com.example.pry_mensual_eq6.modelo

import kotlinx.serialization.Serializable

@Serializable
data class dataDetalleCurso(
    val id: Int,
    val carrera_id : Int,
    val curso: String,
    val codigo: String,
    val descripcion: String,
    val espacio: String,
    val total_horas: String,
    val dias_atencion: String,
    val url_imagen_principal: String?
)