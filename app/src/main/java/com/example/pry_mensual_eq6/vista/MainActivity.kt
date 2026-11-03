package com.example.pry_mensual_eq6.vista

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pry_mensual_eq6.R
import com.example.pry_mensual_eq6.adaptador.CursosAdaptador
import com.example.pry_mensual_eq6.modelo.dataCarreras
import com.example.pry_mensual_eq6.modelo.dataCursos
import com.example.pry_mensual_eq6.presentador.clsCursosPresentador

class MainActivity : AppCompatActivity() {
    private lateinit var presenter: clsCursosPresentador
    private lateinit var spinnerCarreras: Spinner
    private lateinit var recyclerCursos: RecyclerView
    private lateinit var adapterCursos: CursosAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        spinnerCarreras = findViewById(R.id.spnCarreras)
        recyclerCursos = findViewById(R.id.rvCatalogo)

        recyclerCursos.layoutManager = LinearLayoutManager(this)
        adapterCursos = CursosAdaptador(this, emptyList())
        recyclerCursos.adapter = adapterCursos
        presenter = clsCursosPresentador(this)
        configurarListeners()
        presenter.iniciar()
    }

    private fun configurarListeners() {
        spinnerCarreras.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.seleccionarCarrera(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }


    fun mostrarCarreras(carreras: List<dataCarreras>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, carreras)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCarreras.adapter = adapter
    }

    fun mostrarCursos(cursos: List<dataCursos>) {
        adapterCursos.actualizarLista(cursos)
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}