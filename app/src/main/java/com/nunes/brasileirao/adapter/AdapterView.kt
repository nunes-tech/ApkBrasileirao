package com.nunes.brasileirao.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.nunes.brasileirao.R
import com.nunes.brasileirao.databinding.ListaRvClassificacaoBinding
import com.nunes.brasileirao.model_tabela.DadosApiItem


class AdapterView:Adapter<AdapterView.MyViewHolder>() {

    var lista = mutableListOf<DadosApiItem>()

    fun atualizarLista(novaLista:MutableList<DadosApiItem>){
        this.lista = novaLista
        notifyDataSetChanged()
    }

    inner class MyViewHolder(
        val binding :ListaRvClassificacaoBinding
    ):ViewHolder(binding.root) {

       // val logo = binding.imageLogo

        /*
        var posicao = itemView.findViewById<TextView>( R.id.textPosicao )
        var logo = itemView.findViewById<ImageView>( R.id.imageLogo )
        var sigla = itemView.findViewById<TextView>( R.id.textSigra )
        var pontos = itemView.findViewById<TextView>( R.id.textPontos )
        var jogos = itemView.findViewById<TextView>( R.id.textJogos )
        var vitorias = itemView.findViewById<TextView>( R.id.textVitorias )
        var empates = itemView.findViewById<TextView>( R.id.textEmpates )
        var derrotas = itemView.findViewById<TextView>( R.id.textDerrotas )
        var golsSaldo = itemView.findViewById<TextView>( R.id.textGolsSaldo )
        */
    }

    fun bind(holder:MyViewHolder) :ListaRvClassificacaoBinding {
        return holder.binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
       /* val myView = layoutInflater.inflate(
            R.layout.lista_rv_classificacao,
            parent,
            false
        )*/
        val myView = ListaRvClassificacaoBinding.inflate( layoutInflater, parent, false)


        return MyViewHolder(myView)

    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val binding = bind(holder)

        binding.textPosicao.text = lista[position].posicao.toString()
        binding.textSigra.text = lista[position].time.sigla
        binding.textPontos.text = lista[position].pontos.toString()
        binding.textJogos.text = lista[position].jogos.toString()
        binding.textVitorias.text = lista[position].vitorias.toString()
        binding.textEmpates.text = lista[position].empates.toString()
        binding.textDerrotas.text = lista[position].derrotas.toString()
        binding.textGolsSaldo.text = lista[position].saldo_gols.toString()

       /* holder.binding.textPosicao.text = lista[position].posicao.toString()
        holder.binding.textSigra.text = lista[position].time.sigla
        holder.binding.textPontos.text = lista[position].pontos.toString()
        holder.binding.textJogos.text = lista[position].jogos.toString()
        holder.binding.textVitorias.text = lista[position].vitorias.toString()
        holder.binding.textEmpates.text = lista[position].empates.toString()
        holder.binding.textDerrotas.text = lista[position].derrotas.toString()
        holder.binding.textGolsSaldo.text = lista[position].saldo_gols.toString()*/

        /*
     ME DESCUPE PELO CODIGO VERBOSO ABAIXO É PQ ESSA API SÓ DISPONIBILIZA IMAGENS .SVG
     E ESSE TIPO O PICASSO NÃO RODA, ENTÃO ESSA FOI A MANEIRA QUE CONSEGUI PARA COLOCAR AS IMAGENS
      */

        GlideToVectorYou.init()
            .with(binding.imageLogo.context)
            .load(lista[position].time.escudo.toUri(), binding.imageLogo)

    }

}
