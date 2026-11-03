package com.example.pry_mensual_eq6.modelo

import com.example.pry_mensual_eq6.modelo.RetrofitCliente

class clsVistaDetalleModelo {
    suspend fun obtenerDetalleCurso(id: Int): dataDetalleCurso {
        return RetrofitCliente.api.obtenerDetalleCurso(id)
    }

}