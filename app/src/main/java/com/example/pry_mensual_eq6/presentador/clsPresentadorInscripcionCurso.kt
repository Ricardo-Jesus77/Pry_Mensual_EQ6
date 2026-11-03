package com.example.pry_mensual_eq6.presentador

import com.example.pry_mensual_eq6.modelo.RetrofitCliente
import com.example.pry_mensual_eq6.modelo.clsModeloInscripcionCurso

class clsPresentadorInscripcionCurso {

    suspend fun obtenerCursos(): List<clsModeloInscripcionCurso.Curso>? {
        return try {
            val respuesta = RetrofitCliente.api.obtenerCursos()

            if (respuesta.success) {
                respuesta.cursos
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun obtenerInscripciones(): List<clsModeloInscripcionCurso.Inscripcion>? {
        return try {
            val respuesta = RetrofitCliente.api.obtenerInscripciones()

            if (respuesta.success) {
                respuesta.inscripciones
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun crearInscripcion(inscripcion: clsModeloInscripcionCurso.CrearInscripcion): Boolean {
        return try {
            val respuesta = RetrofitCliente.api.crearInscripcion(inscripcion)
            respuesta.success
        } catch (e: Exception) {
            false
        }
    }

    suspend fun eliminarInscripcion(id: Int): Boolean {
        return try {
            val datos = clsModeloInscripcionCurso.EliminarInscripcion(id)
            val respuesta = RetrofitCliente.api.eliminarInscripcion(datos)
            respuesta.success
        } catch (e: Exception) {
            false
        }
    }
}