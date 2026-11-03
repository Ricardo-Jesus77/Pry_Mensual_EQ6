package com.example.pry_mensual_eq6.adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pry_mensual_eq6.modelo.dataCursos
import com.example.pry_mensual_eq6.vista.MainActivity
import com.example.pry_mensual_eq6.R
import com.bumptech.glide.Glide
class CursosAdaptador(  private val activity: MainActivity,
                        private var listaCursos: List<dataCursos>
) : RecyclerView.Adapter<CursosAdaptador.CursoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_curso, parent, false)
        return CursoViewHolder(view)
    }

    class CursoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvCurso: TextView = itemView.findViewById(R.id.txtCurso)
        val tvEspacio: TextView = itemView.findViewById(R.id.txtEspacio)
        val imgCurso: ImageView = itemView.findViewById(R.id.imgCurso)
    }
    override fun onBindViewHolder(holder: CursoViewHolder, position: Int) {
        val curso = listaCursos[position]
        holder.tvCurso.text = curso.curso
        holder.tvEspacio.text = curso.espacio
        val baseUrl = "http://192.168.221.232/cursos_api/"
        val urlImagenCompleta = baseUrl + curso.urlimagenprincipal
        Glide.with(holder.itemView.context)
            .load(urlImagenCompleta)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(holder.imgCurso)

        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {


            }
        })
    }

    override fun getItemCount(): Int {
        return listaCursos.size
    }

    fun actualizarLista(nuevaLista: List<dataCursos>) {
        listaCursos = nuevaLista
        notifyDataSetChanged()
    }
}