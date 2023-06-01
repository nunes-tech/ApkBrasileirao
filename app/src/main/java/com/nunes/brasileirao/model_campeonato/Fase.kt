package com.nunes.brasileirao.model_campeonato

data class Fase(
    val _link: String,
    val chaves: List<Any>,
    val decisivo: Boolean,
    val edicao: Edicao,
    val eliminatorio: Boolean,
    val fase_anterior: Any,
    val fase_id: Int,
    val grupos: List<Any>,
    val ida_e_volta: Boolean,
    val nome: String,
    val proxima_fase: Any,
    val rodadas: List<Any>,
    val slug: String,
    val status: String,
    val tipo: String
)