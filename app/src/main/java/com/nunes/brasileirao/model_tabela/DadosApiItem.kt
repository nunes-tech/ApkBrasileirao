package com.nunes.brasileirao.model_tabela

data class DadosApiItem(
    val aproveitamento: Int,
    val derrotas: Int,
    val empates: Int,
    val gols_contra: Int,
    val gols_pro: Int,
    val jogos: Int,
    val pontos: Int,
    val posicao: Int,
    val saldo_gols: Int,
    val time: Time,
    val ultimos_jogos: List<String>,
    val variacao_posicao: Int,
    val vitorias: Int
)