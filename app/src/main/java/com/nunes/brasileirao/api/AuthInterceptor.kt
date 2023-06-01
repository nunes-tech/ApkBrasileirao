package com.nunes.brasileirao.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor:Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // fazer as alterações na requisição: header, url e etc

        val constructorRequisicao = chain.request().newBuilder()

        //Usando autenticação Bearer
        val requisicaoAlterada = constructorRequisicao
            .addHeader("Authorization", RetrofitServices.TOKEN )
            .build()

        //retornar uma response
        return chain.proceed(requisicaoAlterada)
    }
}