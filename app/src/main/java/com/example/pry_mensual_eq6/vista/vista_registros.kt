package com.example.pry_mensual_eq6.vista

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pry_mensual_eq6.R
import com.example.pry_mensual_eq6.adaptador.RegistrosAdaptador
import com.example.pry_mensual_eq6.modelo.dataCarreras
import com.example.pry_mensual_eq6.modelo.dataEstadoCurso
import com.example.pry_mensual_eq6.presentador.clsRegistrosPresentador

class vista_registros : AppCompatActivity() {
    private lateinit var presenter: clsRegistrosPresentador
    private lateinit var spinnerCarreras: Spinner
    private lateinit var recyclerRegistros: RecyclerView
    private lateinit var adapterRegistros: RegistrosAdaptador
    private lateinit var txtTotal: TextView
    private lateinit var btnVolverCatalogo: Button
    private lateinit var btnRegresar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vista_registros)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        spinnerCarreras = findViewById(R.id.spnCarrerasRegistros)
        recyclerRegistros = findViewById(R.id.rvRegistros)
        txtTotal = findViewById(R.id.txtTotal)
        btnVolverCatalogo = findViewById(R.id.btnVolverCatalogo)
        btnRegresar = findViewById(R.id.btnRegresarRegistros)

        recyclerRegistros.layoutManager = LinearLayoutManager(this)
        adapterRegistros = RegistrosAdaptador(emptyList())
        recyclerRegistros.adapter = adapterRegistros

        presenter = clsRegistrosPresentador(this)
        configurarListeners()
        presenter.iniciar()
    }

    private fun configurarListeners() {
        spinnerCarreras.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.seleccionarCarrera(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        btnVolverCatalogo.setOnClickListener { finish() }
        btnRegresar.setOnClickListener { finish() }
    }

    fun mostrarCarreras(carreras: List<dataCarreras>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, carreras)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCarreras.adapter = adapter
    }

    fun mostrarEstado(lista: List<dataEstadoCurso>) {
        adapterRegistros.actualizarLista(lista)
    }

    fun mostrarTotal(total: Int) {
        txtTotal.text = "Total: " + total.toString()
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}