package com.adrian.demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun btnPress(view:View){
        //Declaración de Views
        val tiet1 = findViewById<TextInputEditText>(R.id.tiet1)
        val tiet2 = findViewById<TextInputEditText>(R.id.tiet2)
        //Validando Autenticación de datos de usuario
        if (tiet1.text.toString().trim()!="" && tiet2.text.toString().trim()!=""){
            val intent = Intent(this,Dashboard::class.java).apply {
                putExtra("usuario",tiet1.text.toString().trim())
            }
            //Iniciando activity "Dashboard"
            startActivity(intent)
        }else{
            //Sino ha ingresado ambos datos de acceso
            Toast.makeText(this,"¡Ingresa datos!",Toast.LENGTH_LONG).show()
        }
    }
}