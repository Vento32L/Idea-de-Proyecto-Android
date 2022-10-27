package uan.edu.musicjmdi

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    val fd by lazy {
        assets.openFd("Mundo Nuevo - Memo el Mc (Audio)(MP3_128K).mp3")
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
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        controllers[ci.play].setOnClickListener(this::playClicked)
        controllers[ci.play].setOnClickListener(this::stopClicked)

    }

    fun playClicked(v: View){
        if(!mp.isPlaying){
            mp.start()
        }
        else{
            mp.pause()
        }
    }

    fun stopClicked(v: View){
        if(mp.isPlaying){
            mp.pause()
        }
        mp.seekTo(0)
    }
}