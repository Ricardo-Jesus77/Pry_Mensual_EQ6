package com.example.pry_mensual_eq6.modelo

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("obtener_cursos.php")
    suspend fun obtenerCursos(): clsModeloInscripcionCurso.RespuestaCursos

    @GET("obtener_inscripciones.php")
    suspend fun obtenerInscripciones(): clsModeloInscripcionCurso.RespuestaInscripciones

    @POST("crear_inscripcion.php")
    suspend fun crearInscripcion(
        @Body inscripcion: clsModeloInscripcionCurso.CrearInscripcion
    ): clsModeloInscripcionCurso.Respuesta

    @POST("eliminar_inscripcion.php")
    suspend fun eliminarInscripcion(
        @Body datos: clsModeloInscripcionCurso.EliminarInscripcion
    ): clsModeloInscripcionCurso.Respuesta
}