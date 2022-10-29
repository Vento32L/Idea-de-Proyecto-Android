package uan.edu.musicjmdi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uan.edu.musicjmdi.databinding.RowSongBinding

class AdaptadorCanciones (val elementos:List<String>, val con: MainActivity):
    RecyclerView.Adapter<AdaptadorCanciones.ViewHolder>()

    class ViewHolder(val bind: RowSongBinding)
        :RecyclerView.ViewHolder(bind.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val v = RowSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(v)
    }

    override fun 




