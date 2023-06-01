package com.nunes.brasileirao.model_campeonato

data class EdicaoAtual(
    val edicao_id: Int,
    val nome: String,
    val nome_popular: String,
    val slug: String,
    val temporada: String
)