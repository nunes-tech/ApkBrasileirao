package com.nunes.brasileirao.model_rodada

data class Rodada(
    val _link: String,
    val nome: String,
    val partidas: List<Partida>,
    val proxima_rodada: ProximaRodada,
    val rodada: Int,
    val rodada_anterior: RodadaAnterior,
    val slug: String,
    val status: String
)