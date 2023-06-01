package com.nunes.brasileirao.model_campeonato

data class Campeonato(
    val campeonato_id: Int,
    val edicao_atual: EdicaoAtual,
    val fase_atual: FaseAtual,
    val fases: List<Fase>,
    val logo: String,
    val nome: String,
    val nome_popular: String,
    val regiao: String,
    val rodada_atual: RodadaAtual,
    val slug: String,
    val status: String,
    val tipo: String
)