package com.example.pry_mensual_eq6.presentador
import com.example.pry_mensual_eq6.modelo.clsVistaDetalleModelo
import com.example.pry_mensual_eq6.modelo.dataDetalleCurso
import com.example.pry_mensual_eq6.vista.vista_detalle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsVistaDetallePresentador (private val vista: vista_detalle) {
    private val modelo = clsVistaDetalleModelo()

    private var detalleCurso: dataDetalleCurso? = null

    fun iniciar(id: Int) {

        CoroutineScope(Dispatchers.Main).launch {
            try {
                detalleCurso = modelo.obtenerDetalleCurso(id)
                vista.mostrarDetalle(detalleCurso!!)
            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }
}