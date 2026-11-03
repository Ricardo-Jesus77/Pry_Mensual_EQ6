package com.example.pry_mensual_eq6.modelo

class clsCursosModelo {
    suspend fun obternerCursosPorCarrera(carreraId: Int): List<dataCursos>{
        return RetrofitCliente.api.obtenerCursos(carreraId)
    }
}