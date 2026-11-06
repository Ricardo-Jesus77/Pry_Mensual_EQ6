package com.example.pry_mensual_eq6.presentador

import com.example.pry_mensual_eq6.modelo.clsCarrerasModelo
import com.example.pry_mensual_eq6.modelo.clsRegistrosModelo
import com.example.pry_mensual_eq6.modelo.dataCarreras
import com.example.pry_mensual_eq6.vista.vista_registros
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsRegistrosPresentador(private val vista: vista_registros) {
    private val modeloCarreras = clsCarrerasModelo()
    private val modeloRegistros = clsRegistrosModelo()
    private var listaCarreras: List<dataCarreras> = emptyList()

    fun iniciar() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                listaCarreras = modeloCarreras.obtenerCarreras()
                vista.mostrarCarreras(listaCarreras)
            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }

    fun seleccionarCarrera(posicion: Int) {
        if (posicion >= 0 && posicion < listaCarreras.size) {
            val carrera = listaCarreras[posicion]
            cargarEstado(carrera.id)
        }
    }

    private fun cargarEstado(carreraId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val lista = modeloRegistros.obtenerEstadoPorCarrera(carreraId)


                var total = 0
                for (estado in lista) {
                    total = total + estado.inscritos
                }

                vista.mostrarEstado(lista)
                vista.mostrarTotal(total)
            } catch (e: Exception) {
                vista.mostrarError("Error al cargar registros: " + e.localizedMessage)
            }
        }
    }
}