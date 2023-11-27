package com.example.conversormedidasalternativo

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var media: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pulgadas = findViewById<EditText>(R.id.pulgadas)
        val centimetros = findViewById<EditText>(R.id.centimetros)
        var convertir = findViewById<Button>(R.id.convertir)
        val resultado = findViewById<TextView>(R.id.resultado)
        val gatito = findViewById<ImageView>(R.id.imageView)

        convertir.setOnClickListener {
            if (pulgadas.text.isEmpty() && !centimetros.text.isEmpty()) {
                resultado.visibility = View.VISIBLE
                val valor = centimetros.text.toString().toDouble() * 0.393701
                resultado.text = valor.toString() + " in"
                pulgadas.text.clear()
                centimetros.text.clear()
                stop()
                play(R.raw.gatito_tierno)

            } else if (!pulgadas.text.isEmpty() && centimetros.text.isEmpty()) {
                resultado.visibility = View.VISIBLE
                val valor = pulgadas.text.toString().toDouble() * 2.54
                resultado.text = valor.toString() + " cm"
                pulgadas.text.clear()
                centimetros.text.clear()
                stop()
                play(R.raw.gatito_tierno)

            } else {
                resultado.visibility = View.VISIBLE
                resultado.text = "Se debe ingresar un valor a convertir"
                stop()
                play(R.raw.gatito_enojado)
            }
        }
    }

    fun play(audio: Int) {
        if (media == null) {
            media = MediaPlayer.create(this, audio)
            media!!.start()
        }
    }

    fun stop() {
        if (media != null) {
            media!!.stop()
            media!!.release()
            media = null
        }
    }
}