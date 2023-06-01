package com.nunes.brasileirao.model_campeonato

data class Edicao(
    val edicao_id: Int,
    val nome: String,
    val nome_popular: String,
    val slug: String,
    val temporada: String
)