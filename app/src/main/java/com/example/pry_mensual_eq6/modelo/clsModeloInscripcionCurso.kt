package com.example.pry_mensual_eq6.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import retrofit2.HttpException

class clsModeloInscripcionCurso {

    @Serializable
    data class Curso(
        val id: Int = 0,
        val nombre: String = "",
        val imagen: String? = null
    )

    @Serializable
    data class Inscripcion(
        val id: Int = 0,
        val matricula: String = "",
        val fecha: String = "",
        val email: String = "",
        @SerialName("curso_id") val cursoId: Int = 0,
        @SerialName("creado_en") val creadoEn: String = "",
        val curso: String = ""
    )

    @Serializable
    data class CrearInscripcion(
        val matricula: String,
        val fecha: String,
        val email: String,
        @SerialName("curso_id") val curso_id: Int
    )

    @Serializable
    data class EliminarInscripcion(
        val id: Int
    )

    @Serializable
    data class RespuestaCursos(
        val success: Boolean = false,
        val cursos: List<Curso> = emptyList()
    )

    @Serializable
    data class RespuestaInscripciones(
        val success: Boolean = false,
        val inscripciones: List<Inscripcion> = emptyList()
    )

    @Serializable
    data class Respuesta(
        val success: Boolean = false,
        val mensaje: String = "",
        val id: Int? = null
    )

    suspend fun crearInscripcion(inscripcion: CrearInscripcion): Respuesta {
        return RetrofitCliente.api.crearInscripcion(inscripcion)
    }

    fun leerMensajeError(e: HttpException): String {
        val texto = e.response()?.errorBody()?.string() ?: ""
        return try {
            val lector = Json { ignoreUnknownKeys = true }
            val respuesta = lector.decodeFromString(Respuesta.serializer(), texto)
            respuesta.mensaje
        } catch (ex: Exception) {
            "Error del servidor: " + e.code()
        }
    }
}