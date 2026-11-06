package com.example.pry_mensual_eq6.presentador

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.pry_mensual_eq6.modelo.clsCarrerasModelo
import com.example.pry_mensual_eq6.modelo.clsCursosModelo
import com.example.pry_mensual_eq6.modelo.dataCarreras
import com.example.pry_mensual_eq6.modelo.dataCursos
import com.example.pry_mensual_eq6.vista.MainActivity

class clsCursosPresentador(private val vista: MainActivity) {
    private val modelo = clsCarrerasModelo()
    private var listaCarreras: List<dataCarreras> = emptyList()
    private val modeloProductos = clsCursosModelo()

    fun iniciar() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                listaCarreras = modelo.obtenerCarreras()
                vista.mostrarCarreras(listaCarreras)
            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }

    fun seleccionarCarrera(posicion: Int) {
        if (posicion >= 0 && posicion < listaCarreras.size) {
            val carrera = listaCarreras[posicion]
            cargarCursos(carrera.id)
        }
    }

    private fun cargarCursos(carreraId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val cursos = modeloProductos.obtenerCursosPorCarrera(carreraId)
                vista.mostrarCursos(cursos)
            } catch (e: Exception) {
                vista.mostrarError("Error al cargar cursos: " + e.localizedMessage)
            }
        }
    }


    fun seleccionarCurso(curso: dataCursos) {
        vista.abrirDetalleCurso(curso.id)
    }


    fun registrarCurso(curso: dataCursos) {
        vista.abrirInscripcion(curso.id, curso.curso, curso.urlimagenprincipal)
    }
}