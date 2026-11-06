package com.example.pry_mensual_eq6.modelo

class clsRegistrosModelo {
    suspend fun obtenerEstadoPorCarrera(carreraId: Int): List<dataEstadoCurso> {
        return RetrofitCliente.api.obtenerEstadoInscripciones(carreraId)
    }
}