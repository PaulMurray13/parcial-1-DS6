package com.example.demo_list_v2

data class Cancion(
    val titulo: String,
    val artista: String,
    val album: String,
    val genero: String,
    val duracion: String,
    val fechaLanzamiento: String,
    val imagenRes: Int,
    val audioRes: Int
)