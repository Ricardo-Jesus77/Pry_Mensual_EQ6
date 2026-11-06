package com.example.pry_mensual_eq6.vista

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pry_mensual_eq6.modelo.dataDetalleCurso
import com.example.pry_mensual_eq6.presentador.clsVistaDetallePresentador
import com.example.pry_mensual_eq6.R
import com.bumptech.glide.Glide
import com.example.pry_mensual_eq6.modelo.RetrofitCliente
class vista_detalle : AppCompatActivity (){
    private lateinit var presentador: clsVistaDetallePresentador

    private lateinit var btnBack: Button
    private lateinit var imgCurso: ImageView
    private lateinit var txtNomCurso: TextView
    private lateinit var txtCodigo: TextView
    private lateinit var txtDescripcion: TextView
    private lateinit var txtEspacioCurso: TextView
    private lateinit var txtHoras: TextView
    private lateinit var txtDias: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_vista_detalle)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnBack = findViewById(R.id.btnBack)
        imgCurso = findViewById(R.id.imgCurso)
        txtNomCurso = findViewById(R.id.txtNomCurso)
        txtCodigo = findViewById(R.id.txtCodigo)
        txtDescripcion = findViewById(R.id.txtDescripcion)
        txtEspacioCurso = findViewById(R.id.txtEspacioCurso)
        txtHoras = findViewById(R.id.txtHoras)
        txtDias = findViewById(R.id.txtDias)

        btnBack.setOnClickListener {
            finish()
        }
        presentador = clsVistaDetallePresentador(this)


        val idCurso = intent.getIntExtra("idCurso", -1)

        if (idCurso != -1) {
            presentador.iniciar(idCurso)
        } else {
            mostrarError("No se recibió el ID del curso")
        }
    }
    fun mostrarDetalle(curso: dataDetalleCurso) {
        txtNomCurso.text = curso.curso.uppercase()
        txtCodigo.text = "Código: " + curso.codigo
        // descripcion ahora es String? (puede ser null), por eso el ?: sí tiene efecto
        txtDescripcion.text = curso.descripcion ?: "Sin descripción"
        txtEspacioCurso.text = curso.espacio
        txtHoras.text = curso.total_horas + " Horas"
        txtDias.text = curso.dias_atencion


        val urlImagen = RetrofitCliente.BASE_URL + (curso.url_imagen_principal ?: "")
        Glide.with(this)
            .load(urlImagen)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(imgCurso)
    }
        fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }


}