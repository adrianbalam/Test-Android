package com.adrian.demo

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.io.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and
import kotlin.experimental.or

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        bienvenida()
        generarCodigoBarras()
    }

    private fun bienvenida(){
        //Declaración e instancia de Views
        val tvBienvenida = findViewById<TextView>(R.id.tvBienvenida)
        //Recuperando el nombre del usuario
        val nombre = intent.getStringExtra("usuario")
        //Definiendo nombre como valor de texto del TV
        tvBienvenida.setText("Bienvenido(a) $nombre")
    }

    private fun generarCodigoBarras(){
        //Definiendo Token
        val _TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIU"
        //Declaración e instancia de Views
        val ivcb:ImageView = findViewById(R.id.ivcb)
        val tvToken:TextView = findViewById(R.id.tvToken)
        //Inicializando objetivo convertidor a código de barras
        val bce:BarcodeEncoder = BarcodeEncoder()
        //Construyendo la "imagen" del código de barras
        val bm:Bitmap = bce.encodeBitmap(convertirAHash(_TOKEN),BarcodeFormat.CODE_128,700,300)
        //Definiendo el bitmap en el ImageView
        ivcb.setImageBitmap(bm)
        //Definiendo el valor del token en el TextView
        tvToken.setText(_TOKEN)
    }

    fun convertirAHash(md5: String): String? {
        try {
            //Inicializando el formato de hasheo
            val md = MessageDigest.getInstance("MD5")
            //Definiendo compatibilidad con carácteres especiales
            val array = md.digest(md5.toByteArray(charset("UTF-8")))
            //Comenzando proceso de conversión
            val sb = StringBuffer()
            for (i in array.indices) {
                sb.append(array[i])
            }
            //Retornando valor hasheado
            return sb.toString()
            //Manejo de excepciones
        } catch (e: NoSuchAlgorithmException) {
        } catch (ex: UnsupportedEncodingException) {
        }
        return null
    }

}

