package com.nunes.brasileirao.model_rodada

data class Partida(
    val _link: String,
    val campeonato: Campeonato,
    val data_realizacao: String,
    val data_realizacao_iso: String,
    val disputa_penalti: Boolean,
    val estadio: Estadio,
    val hora_realizacao: String,
    val partida_id: Int,
    val placar: String,
    val placar_mandante: Int,
    val placar_visitante: Int,
    val slug: String,
    val status: String,
    val time_mandante: TimeMandante,
    val time_visitante: TimeVisitante
)