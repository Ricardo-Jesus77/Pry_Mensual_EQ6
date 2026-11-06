package com.example.pry_mensual_eq6.adaptador

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pry_mensual_eq6.R
import com.example.pry_mensual_eq6.modelo.RetrofitCliente
import com.example.pry_mensual_eq6.modelo.dataEstadoCurso

class RegistrosAdaptador(
    private var listaEstado: List<dataEstadoCurso>
) : RecyclerView.Adapter<RegistrosAdaptador.RegistroViewHolder>() {

    class RegistroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgCurso: ImageView = itemView.findViewById(R.id.imgCursoRegistro)
        val tvCurso: TextView = itemView.findViewById(R.id.txtCursoRegistro)
        val pbCupo: ProgressBar = itemView.findViewById(R.id.pbCupo)
        val tvInscritos: TextView = itemView.findViewById(R.id.txtInscritos)
        val tvCupo: TextView = itemView.findViewById(R.id.txtCupo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_registro, parent, false)
        return RegistroViewHolder(view)
    }

    override fun onBindViewHolder(holder: RegistroViewHolder, position: Int) {
        val estado = listaEstado[position]
        holder.tvCurso.text = estado.curso.uppercase()
        holder.tvInscritos.text = estado.inscritos.toString() + " inscritos"
        holder.tvCupo.text = "/ " + estado.cupo.toString() + " total"

        if (estado.cupo > 0) {
            holder.pbCupo.max = estado.cupo
        } else {
            holder.pbCupo.max = 1
        }
        holder.pbCupo.progress = estado.inscritos

        val contexto = holder.itemView.context
        val color = if (estado.inscritos * 100 >= estado.cupo * 80) R.color.verde else R.color.azul
        holder.pbCupo.progressTintList = ColorStateList.valueOf(ContextCompat.getColor(contexto, color))

        Glide.with(contexto)
            .load(RetrofitCliente.BASE_URL + estado.urlimagenprincipal)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(holder.imgCurso)
    }

    override fun getItemCount(): Int {
        return listaEstado.size
    }

    fun actualizarLista(nuevaLista: List<dataEstadoCurso>) {
        listaEstado = nuevaLista
        notifyDataSetChanged()
    }
}