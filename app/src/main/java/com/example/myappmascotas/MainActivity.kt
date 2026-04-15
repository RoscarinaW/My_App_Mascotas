package com.example.myappmascotas

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    private var mascotaAtual: Mascotas? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinnerTipo = findViewById<Spinner>(R.id.spinnerTipo)
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etEdad = findViewById<EditText>(R.id.etEdad)
        val btnCrear = findViewById<Button>(R.id.btnCrear)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val btnLadrar = findViewById<Button>(R.id.btnLadrar)
        val btnMaullar = findViewById<Button>(R.id.btnMaullar)

        val opciones = arrayOf("Perro", "Gato")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTipo.adapter = adapter

        spinnerTipo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val seleccion = opciones[position]
                if (seleccion == "Perro") {
                    btnLadrar.visibility = View.VISIBLE
                    btnMaullar.visibility = View.GONE
                } else if (seleccion == "Gato") {
                    btnLadrar.visibility = View.GONE
                    btnMaullar.visibility = View.VISIBLE
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        btnCrear.setOnClickListener {
            val nombreInput = etNombre.text.toString()
            val edadInputStr = etEdad.text.toString()

            if (nombreInput.isEmpty() || edadInputStr.isEmpty()) {
                Toast.makeText(this, "Por favor llenar todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val edadInput = edadInputStr.toInt()
            val tipoSeleccionado = spinnerTipo.selectedItem.toString()

            val nuevaMascota: Mascotas = if (tipoSeleccionado == "Perro") {
                Mascotas.Perro()
            } else {
                Mascotas.Gato()
            }

            nuevaMascota.setNombre(nombreInput)

            val edadValida =nuevaMascota.setEdad(edadInput)
            if (!edadValida) {
                Toast.makeText(this, "Error, la edad no puede ser un numero negativo", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            mascotaAtual = nuevaMascota
            tvResultado.text = nuevaMascota.mostarInfo()
        }

        btnLadrar.setOnClickListener {
            if (mascotaAtual is Mascotas.Perro){
                val perro = mascotaAtual as Mascotas.Perro
                Toast.makeText(this, perro.ladrar(), Toast.LENGTH_SHORT).show()
            }
        }

        btnMaullar.setOnClickListener {
            if (mascotaAtual is Mascotas.Gato) {
                val gato = mascotaAtual as Mascotas.Gato
                Toast.makeText(this, gato.maullar(), Toast.LENGTH_SHORT).show()
            }
        }

    }
}