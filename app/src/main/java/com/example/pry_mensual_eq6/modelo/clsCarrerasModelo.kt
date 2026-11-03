package com.example.pry_mensual_eq6.modelo

class clsCarrerasModelo {
    suspend fun obtenerCarreras(): List<dataCarreras> {
        val datos = RetrofitCliente.api.obtenerCarreras()

        val listaFinal = mutableListOf(dataCarreras(0, "Todos los cursos"))
        listaFinal.addAll(datos)

        return listaFinal
    }
}