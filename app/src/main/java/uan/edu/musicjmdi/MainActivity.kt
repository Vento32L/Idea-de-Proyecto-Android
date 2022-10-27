package uan.edu.musicjmdi

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.TextureView
import android.view.View
import android.widget.TextView
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    val fd by lazy {
        assets.openFd(cancionActual)
    }

    val mp by lazy {
        val m = MediaPlayer()
        m.setDataSource(
            fd.fileDescriptor,
            fd.startOffset,
            fd.length
        )
        fd.close()
        m.prepare()
        m
    }

    val controllers by lazy{
        listOf(R.id.prev, R.id.stop, R.id.play, R.id.next).map {
            findViewById<MaterialButton>(it)
        }
    }

    object ci{
        val prev = 0
        val stop = 1
        val play = 2
        val next = 3
    }

    val nombreCancion by lazy {
        findViewById<TextView>(R.id.nombreCancion)
    }

    val canciones by lazy {
        val nombreFicheros = assets.list("")?.toList() ?: listOf()
        nombreFicheros.filter { it.contains(".mp3") }
    }

    var cancionesActualIndex = 0
        set(value) {
            var v = if(value == -1){
                canciones.size-1
            }
            else{
                value % canciones.size
            }
            field = v
            cancionActual = canciones[v]
        }

    lateinit var cancionActual: String
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        controllers[ci.play].setOnClickListener(this::playClicked)
        controllers[ci.play].setOnClickListener(this::stopClicked)
        cancionActual = canciones[cancionesActualIndex]

    }

    fun playClicked(v: View){
        if(!mp.isPlaying){
            mp.start()
            controllers[ci.play].setIconResource(R.drawable.ic_baseline_pause_48)
            nombreCancion.visibility = View.VISIBLE
        }
        else{
            mp.pause()
            controllers[ci.play].setIconResource(R.drawable.ic_baseline_play_arrow_48)
        }
    }

    fun stopClicked(v: View){
        if(mp.isPlaying){
            mp.pause()
            controllers[ci.play].setIconResource(R.drawable.ic_baseline_play_arrow_48)
            nombreCancion.visibility = View.INVISIBLE
        }
        mp.seekTo(0)
    }
}