package com.example.myappmascotas

open class Mascotas {

    private var nombre: String = ""
    private  var edad: Int = 0

    fun getNombre(): String = nombre
    fun getEdad(): Int = edad

    fun setNombre(nuevoNombre: String){
        this.nombre = nuevoNombre
    }
    fun setEdad(nuevaEdad: Int): Boolean {
        return  if(nuevaEdad >= 0){
            this.edad = nuevaEdad
            true
        } else {
            false
        }
    }

    open fun mostarInfo(): String {
        return "Mascota: $nombre, Edad: $edad"
    }

    class Perro : Mascotas() {
        override fun mostarInfo(): String {
            return "Soy un Perrito llamado ${getNombre()} y tengo ${getEdad()} años. ¡Guau, guau!"
        }
    }

    fun ladrar(): String {
        return "¡Guau, guau!"
    }

    class Gato : Mascotas() {
        override fun mostarInfo(): String {
            return "Soy un Gatito llamado ${getNombre()} y tengo ${getEdad()} años. ¡Miau, miau!"
        }
    }

    fun maullar(): String {
        return "¡Miau, miau!"
    }
}