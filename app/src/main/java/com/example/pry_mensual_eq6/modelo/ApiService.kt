package com.example.pry_mensual_eq6.modelo

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("obtener_carreras.php")
    suspend fun obtenerCarreras(): List<dataCarreras>
    @GET("obtener_cursos.php")
    suspend fun obtenerCursos(@Query("carrera_id") carreraId : Int): List<dataCursos>



    @GET("obtener_cursos.php")
    suspend fun obtenerCursos2(): clsModeloInscripcionCurso.RespuestaCursos

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

    @GET("api_detalle.php")
    suspend fun obtenerDetalleCurso( @Query("id") id: Int ): dataDetalleCurso
}