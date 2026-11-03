package com.example.pry_mensual_eq6.vista

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.pry_mensual_eq6.R
import com.example.pry_mensual_eq6.modelo.clsModeloInscripcionCurso
import com.example.pry_mensual_eq6.presentador.clsPresentadorInscripcionCurso
import kotlinx.coroutines.launch
import java.util.Calendar

class InscripcionCursos : AppCompatActivity() {

    private lateinit var imgCurso: ImageView
    private lateinit var txtNombreCurso: TextView
    private lateinit var edtMatricula: EditText
    private lateinit var edtFecha: EditText
    private lateinit var edtEmail: EditText
    private lateinit var btnConfirmar: Button
    private lateinit var btnCancelar: Button

    private val presentador = clsPresentadorInscripcionCurso()

    private var cursoId: Int = 0
    private var cursoNombre: String = ""
    private var cursoImagen: String = ""

    private val URL_IMAGENES = "http://10.0.2.2/cursos_api/imagenes/"

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
        edtMatricula = findViewById(R.id.edtMatricula)
        edtFecha = findViewById(R.id.edtFecha)
        edtEmail = findViewById(R.id.edtEmail)
        btnConfirmar = findViewById(R.id.btnConfirmar)
        btnCancelar = findViewById(R.id.btnCancelar)

        cursoId = intent.getIntExtra("curso_id", 0)
        cursoNombre = intent.getStringExtra("curso_nombre") ?: ""
        cursoImagen = intent.getStringExtra("curso_imagen") ?: ""

        mostrarDatosCurso()

        edtFecha.setOnClickListener { mostrarFecha() }
        btnConfirmar.setOnClickListener { confirmarInscripcion() }
        btnCancelar.setOnClickListener { limpiarFormulario() }
    }

    private fun mostrarDatosCurso() {
        txtNombreCurso.text = cursoNombre

        if (cursoImagen.isNotEmpty()) {
            val url = if (cursoImagen.startsWith("http")) {
                cursoImagen
            } else {
                URL_IMAGENES + cursoImagen
            }

            imgCurso.load(url) {
                crossfade(true)
                error(android.R.drawable.ic_menu_report_image)
            }
        }
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

    private fun confirmarInscripcion() {
        val matricula = edtMatricula.text.toString().trim()
        val fecha = edtFecha.text.toString().trim()
        val email = edtEmail.text.toString().trim()

        if (matricula.isEmpty() || fecha.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Ingresa un correo válido", Toast.LENGTH_SHORT).show()
            return
        }

        val inscripcion = clsModeloInscripcionCurso.CrearInscripcion(
            matricula = matricula,
            fecha = fecha,
            email = email,
            curso_id = cursoId
        )

        btnConfirmar.isEnabled = false

        lifecycleScope.launch {
            val resultado = presentador.crearInscripcion(inscripcion)

            btnConfirmar.isEnabled = true

            if (resultado) {
                Toast.makeText(
                    this@InscripcionCursos,
                    "Inscripción registrada correctamente",
                    Toast.LENGTH_LONG
                ).show()
                limpiarFormulario()
            } else {
                Toast.makeText(
                    this@InscripcionCursos,
                    "No se pudo registrar la inscripción",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun limpiarFormulario() {
        edtMatricula.setText("")
        edtFecha.setText("")
        edtEmail.setText("")
    }
}