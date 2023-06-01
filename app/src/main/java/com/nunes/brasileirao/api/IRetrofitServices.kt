package com.nunes.brasileirao.api

import com.nunes.brasileirao.model_campeonato.Campeonato
import com.nunes.brasileirao.model_rodada.Rodada
import com.nunes.brasileirao.model_tabela.DadosApiItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface IRetrofitServices {

    @GET("campeonatos/{id_campeonato}/tabela")
    suspend fun getTabela(
        @Path("id_campeonato") id_campeonato:Int
    ) :Response<MutableList<DadosApiItem>>

    @GET("campeonatos/{campeonato_id}")
    suspend fun getRodadaAtual(
        @Path("campeonato_id") campeonato_id:Int = 10
    ):Response<Campeonato>

    @GET("campeonatos/{campeonato_id}/rodadas/{rodada}")
    suspend fun getRodada(
        @Path("campeonato_id") campeonato_id:Int,
        @Path("rodada") rodada:Int

    ):Response<Rodada>  //:Response<MutableList<Rodada>> //antes

}