package com.example.pry_mensual_eq6.modelo

import com.google.gson.annotations.SerializedName

class clsModeloInscripcionCurso {

    data class Curso(
        val id: Int,
        val nombre: String
    )

    data class Inscripcion(
        val id: Int,
        val matricula: String,
        val fecha: String,
        val email: String,

        @SerializedName("curso_id")
        val cursoId: Int,

        @SerializedName("creado_en")
        val creadoEn: String,

        val curso: String = ""
    )

    data class CrearInscripcion(
        val matricula: String,
        val fecha: String,
        val email: String,

        @SerializedName("curso_id")
        val curso_id: Int
    )

    data class EliminarInscripcion(
        val id: Int
    )

    data class RespuestaCursos(
        val success: Boolean,
        val cursos: List<Curso>
    )

    data class RespuestaInscripciones(
        val success: Boolean,
        val inscripciones: List<Inscripcion>
    )

    data class Respuesta(
        val success: Boolean,
        val mensaje: String,
        val id: Int? = null
    )
}