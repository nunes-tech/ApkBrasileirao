package com.nunes.brasileirao.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.nunes.brasileirao.R
import com.nunes.brasileirao.api.RetrofitServices
import com.nunes.brasileirao.databinding.ActivityMainBinding
import com.nunes.brasileirao.fragments.ClassificacaoFragment
import com.nunes.brasileirao.fragments.RodadasFragment
import com.nunes.brasileirao.model_rodada.Rodada
import com.nunes.brasileirao.model_tabela.DadosApiItem
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val retrofit by lazy {
        RetrofitServices.retrofit
    }

    lateinit var jobTeabela: Job
    lateinit var jobRodada: Job
    var numeroDaRodadaAtual = 0
    var tabela:MutableList<DadosApiItem>? = null
    var rodadaRecuperada = false
    var rodadaAtualRecuperada:Rodada? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.clBotoesRodadas.visibility = View.GONE

        supportActionBar?.hide()

        jobTeabela = CoroutineScope(Dispatchers.IO).launch {

         tabela = buscarTabela()

        }

        binding.btnClassificacao.setOnClickListener {

            binding.clBotoesRodadas.visibility = View.GONE

            binding.linearLayoutTopo.visibility = View.VISIBLE

       CoroutineScope(Dispatchers.IO).launch {
           reexibirTabela(tabela!!)
       }

        }

        binding.btnRodadas.setOnClickListener {

            binding.clBotoesRodadas.visibility = View.VISIBLE

            binding.linearLayoutTopo.visibility = View.GONE

            jobRodada = CoroutineScope(Dispatchers.IO).launch {

            if (rodadaRecuperada) {

                if (rodadaAtualRecuperada != null) {

                    reexibirRodada(rodadaAtualRecuperada!!)

                    MainScope().launch {
                        binding.btnRodada.text = "Rodada: " + rodadaAtualRecuperada?.rodada
                    }
                }

            } else {

                numeroDaRodadaAtual = numeroRodadaAtual()
                Log.i("teste_fut", "$numeroDaRodadaAtual")

                if (numeroDaRodadaAtual != null) {

                   rodadaAtualRecuperada = buscarRodada(numeroDaRodadaAtual)

                    MainScope().launch {
                        binding.btnRodada.text = "Rodada: " + rodadaAtualRecuperada?.rodada
                    }

                }

            }

            }
        }

        binding.imageButtonAnterior.setOnClickListener {

            if (numeroDaRodadaAtual > 1 && numeroDaRodadaAtual < 38) {
                numeroDaRodadaAtual -= 1

                try {

                    CoroutineScope(Dispatchers.IO).launch {
                        buscarOutraRodada(numeroDaRodadaAtual)
                        MainScope().launch {
                            binding.btnRodada.text = "Rodada: " + numeroDaRodadaAtual
                        }
                    }

                } catch (e:Exception) {
                    Log.i("teste_fut", "Navegando por rodadas")
                }

            }

        }

        binding.imageButtonProxima.setOnClickListener {
            if (numeroDaRodadaAtual >= 1 && numeroDaRodadaAtual < 38) {
                numeroDaRodadaAtual += 1

                try {

                    CoroutineScope(Dispatchers.IO).launch {
                        buscarOutraRodada(numeroDaRodadaAtual)
                        MainScope().launch {
                            binding.btnRodada.text = "Rodada: " + numeroDaRodadaAtual
                        }
                    }

                } catch (e:Exception) {
                    Log.i("teste_fut", "Navegando por rodadas")
                }

            }
        }

    }

  /*  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.meu_menu, menu)
        return true
    }*/

 /*   override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item -> Log.i("teste", "mensagem...")
        }
        return true
    }*/

    private suspend fun buscarTabela() : MutableList<DadosApiItem>? {

        var corpo: MutableList<DadosApiItem>? = null

        try {
            val retorno = retrofit.getTabela(10)
            if (retorno.isSuccessful) {

                 corpo = retorno.body()

                if (corpo != null && corpo.isNotEmpty()) {

                    withContext(Dispatchers.Main){

                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.fragmentConteudo, ClassificacaoFragment(corpo))
                            .commit()

                    }

                }

            }
        } catch (e:Exception) {
            e.printStackTrace()
            return null
        }

        return corpo

    }



    suspend fun numeroRodadaAtual():Int{

        var rodada = 0

        try {
            val retorno = retrofit.getRodadaAtual(10)

            if (retorno.isSuccessful){
                val corpo = retorno.body()

               val rodada1 = corpo?.rodada_atual?.rodada

                if (rodada1 != null) {
                    rodada = rodada1
                }

                Log.i("teste_fut", "rodadaAtual: $rodada")
            }


        } catch (e:Exception){
            e.printStackTrace()
            Log.i("teste_fut", "Erro ao recuperar rodada atual")
            return 0
        }

        //if (rodada != null) return rodada else return 0
        return rodada
    }

    @SuppressLint("SuspiciousIndentation")
    suspend fun buscarRodada(nRodada:Int) :Rodada? {
        var corpo:Rodada? = null

        try {

            if (nRodada != null) {

              val retorno =  retrofit.getRodada(10, nRodada )

                if (retorno.isSuccessful){

                    Log.i("teste_fut", "Rodada atual: ${retorno.code()}")

                    corpo = retorno.body()

                        if (corpo != null) {

                            rodadaRecuperada = true

                        Log.i("teste_fut", "Rodada atual: ${corpo.status}")

                        withContext(Dispatchers.Main){

                            supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.fragmentConteudo, RodadasFragment(corpo))
                                .commit()

                        }

                    }

                }
            }


        } catch (e:Exception) {
            e.printStackTrace()
            Log.i("teste_fut", "buscarRodadaAtual: Erro ao tentar buscar rodada atual")
            return null
        }
            return corpo
    }



    suspend fun reexibirTabela( tabela:MutableList<DadosApiItem> ){

        try {
                    withContext(Dispatchers.Main){

                        if (tabela != null && tabela.isNotEmpty()) {

                            supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.fragmentConteudo, ClassificacaoFragment(tabela))
                                .commit()

                        }
                    }

        } catch (e:Exception) {
            e.printStackTrace()
        }

    }

    suspend fun reexibirRodada(rodada:Rodada){

        if (rodada != null) {
            withContext(Dispatchers.Main){

                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentConteudo, RodadasFragment(rodada))
                    .commit()

            }
        }

    }


    suspend fun buscarOutraRodada(nRodada:Int) :Rodada? {
        var corpo:Rodada? = null

        if(nRodada in 1..38) {
            try {

                if (nRodada != null) {

                    val retorno =  retrofit.getRodada(10, nRodada )

                    if (retorno.isSuccessful){

                        Log.i("teste_fut", "Sucesso codigo de retorno: ${retorno.code()}")

                        corpo = retorno.body()

                        if (corpo != null) {

                            Log.i("teste_fut", "Status da Rodada: ${corpo.status}")

                            withContext(Dispatchers.Main){

                                supportFragmentManager
                                    .beginTransaction()
                                    .replace(R.id.fragmentConteudo, RodadasFragment(corpo))
                                    .commit()

                            }

                        }

                    }
                }


            } catch (e:Exception) {
                e.printStackTrace()
                Log.i("teste_fut", "buscarOutraRodada: Erro ao tentar buscar outra rodada")
                return null
            }

        }
        return corpo
    }


}