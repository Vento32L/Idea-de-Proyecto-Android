package uan.edu.musicjmdi

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uan.edu.musicjmdi.databinding.RowSongBinding

class AdaptadorCanciones (val elementos:List<String>, val con: MainActivity):
    RecyclerView.Adapter<AdaptadorCanciones.ViewHolder>(){

    var selected = -1

    class ViewHolder(val bind: RowSongBinding)
        :RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val v = RowSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val elem = elementos[position]
        with(holder.bind){
            rowNombreCancion.text = elem
            if(position==selected){
                rowListaCanciones.setBackgroundColor(Color.LTGRAY)
            }
            else{
                rowListaCanciones.setBackgroundColor(Color.WHITE)
            }
            rowListaCanciones.setOnClickListener{
                con.cancionActualIndex = position
                con.refreshSong()
                selected = position
                //rowListaCanciones.setBackgroundColor(Color.LTGRAY)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return elementos.size
    }
}




