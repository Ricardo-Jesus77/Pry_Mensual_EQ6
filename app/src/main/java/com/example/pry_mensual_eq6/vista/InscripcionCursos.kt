package com.example.pry_mensual_eq6.vista

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.pry_mensual_eq6.R
import com.example.pry_mensual_eq6.modelo.RetrofitCliente
import com.example.pry_mensual_eq6.presentador.clsPresentadorInscripcionCurso
import java.util.Calendar

class InscripcionCursos : AppCompatActivity() {

    private lateinit var imgCurso: ImageView
    private lateinit var txtNombreCurso: TextView
    private lateinit var txtEncabezado: TextView
    private lateinit var edtMatricula: EditText
    private lateinit var edtFecha: EditText
    private lateinit var edtEmail: EditText
    private lateinit var btnConfirmar: Button
    private lateinit var btnCancelar: Button
    private lateinit var btnRegresar: Button

    private lateinit var presentador: clsPresentadorInscripcionCurso

    private var cursoId: Int = 0
    private var cursoNombre: String = ""
    private var cursoImagen: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_inscripcion_cursos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imgCurso = findViewById(R.id.img_curso)
        txtNombreCurso = findViewById(R.id.txtNombreCurso)
        txtEncabezado = findViewById(R.id.txtEncabezado)
        edtMatricula = findViewById(R.id.edtMatricula)
        edtFecha = findViewById(R.id.edtFecha)
        edtEmail = findViewById(R.id.edtEmail)
        btnConfirmar = findViewById(R.id.btnConfirmar)
        btnCancelar = findViewById(R.id.btnCancelar)
        btnRegresar = findViewById(R.id.btnRegresar)

        cursoId = intent.getIntExtra("curso_id", 0)
        cursoNombre = intent.getStringExtra("curso_nombre") ?: ""
        cursoImagen = intent.getStringExtra("curso_imagen") ?: ""

        presentador = clsPresentadorInscripcionCurso(this)

        mostrarDatosCurso()

        edtFecha.setOnClickListener { mostrarFecha() }
        btnConfirmar.setOnClickListener(this::ejecutarConfirmar)
        btnCancelar.setOnClickListener { finish() } // regresa al catálogo
        btnRegresar.setOnClickListener { finish() }
    }

    fun ejecutarConfirmar(v: View) {
        presentador.confirmarInscripcion(
            edtMatricula.text.toString().trim(),
            edtFecha.text.toString().trim(),
            edtEmail.text.toString().trim(),
            cursoId
        )
    }

    private fun mostrarDatosCurso() {
        txtEncabezado.text = "INSCRIPCIÓN:\n" + cursoNombre.uppercase()
        txtNombreCurso.text = cursoNombre.uppercase()

        val url = if (cursoImagen.startsWith("http")) {
            cursoImagen
        } else {
            RetrofitCliente.BASE_URL + cursoImagen
        }
        Glide.with(this)
            .load(url)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_report_image)
            .into(imgCurso)
    }

    private fun mostrarFecha() {
        val calendario = Calendar.getInstance()
        DatePickerDialog(
            this,
            { _, anio, mes, dia ->
                edtFecha.setText(String.format("%04d-%02d-%02d", anio, mes + 1, dia))
            },
            calendario.get(Calendar.YEAR),
            calendario.get(Calendar.MONTH),
            calendario.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    fun mostrarExito(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
        limpiarFormulario()
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }

    fun bloquearBoton(bloquear: Boolean) {
        btnConfirmar.isEnabled = !bloquear
    }

    private fun limpiarFormulario() {
        edtMatricula.setText("")
        edtFecha.setText("")
        edtEmail.setText("")
    }
}