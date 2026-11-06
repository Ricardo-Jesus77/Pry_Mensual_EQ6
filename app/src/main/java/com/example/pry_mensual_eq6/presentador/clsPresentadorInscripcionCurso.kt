package com.example.pry_mensual_eq6.presentador

import android.util.Patterns
import com.example.pry_mensual_eq6.modelo.clsModeloInscripcionCurso
import com.example.pry_mensual_eq6.vista.InscripcionCursos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

class clsPresentadorInscripcionCurso(private val vista: InscripcionCursos) {
    private val modelo = clsModeloInscripcionCurso()

    fun confirmarInscripcion(matricula: String, fecha: String, email: String, cursoId: Int) {

        if (matricula.isEmpty() || fecha.isEmpty() || email.isEmpty()) {
            vista.mostrarError("Completa todos los campos")
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            vista.mostrarError("Ingresa un correo válido")
            return
        }
        if (cursoId <= 0) {
            vista.mostrarError("No se recibió el curso seleccionado")
            return
        }


        val inscripcion = clsModeloInscripcionCurso.CrearInscripcion(
            matricula = matricula,
            fecha = fecha,
            email = email,
            curso_id = cursoId
        )

        vista.bloquearBoton(true)

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modelo.crearInscripcion(inscripcion)
                if (respuesta.success) {
                    vista.mostrarExito(respuesta.mensaje)
                } else {
                    vista.mostrarError(respuesta.mensaje)
                }
            } catch (e: HttpException) {

                vista.mostrarError(modelo.leerMensajeError(e))
            } catch (e: Exception) {

                vista.mostrarError("Error: " + e.localizedMessage)
            }
            vista.bloquearBoton(false)
        }
    }
}