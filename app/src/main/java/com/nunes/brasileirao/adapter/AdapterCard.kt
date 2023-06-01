package com.nunes.brasileirao.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.nunes.brasileirao.R
import com.nunes.brasileirao.databinding.CardViewRodadasBinding
import com.nunes.brasileirao.databinding.ListaRvClassificacaoBinding
import com.nunes.brasileirao.model_rodada.Partida
import com.nunes.brasileirao.model_rodada.Rodada

class AdapterCard:Adapter<AdapterCard.MyViewHolder>() {

    var lista = mutableListOf<Partida>()

    fun atualizarLista(rodada:Rodada){

        this.lista = rodada.partidas.toMutableList()
        notifyDataSetChanged()

    }


    inner class MyViewHolder(
        val binding:CardViewRodadasBinding
        ) :ViewHolder(binding.root){

       /* var dataRealizacao = binding.findViewById<TextView>(R.id.textDataHora)
        var horaRealizacao = binding.findViewById<TextView>(R.id.textHora)
        var timeCasa = binding.findViewById<TextView>(R.id.textTimeCasa)
        var timeVisitante = binding.findViewById<TextView>(R.id.textTimeVisitante)
        var placarCasa = binding.findViewById<TextView>(R.id.textPlacarCasa)
        var placarVisitante = binding.findViewById<TextView>(R.id.textPlacarVisitante)
        var statusJogo = binding.findViewById<Button>(R.id.btnStatusJogo)
        var estadioJogo = binding.findViewById<TextView>(R.id.textEstadioJogo)
        var imagemTimeCasa = binding.findViewById<ImageView>(R.id.imageTimeCasa)
        var imagemTimeVisitante = binding.findViewById<ImageView>(R.id.imageTimeVisitante)*/

    }

    fun bind(holder: MyViewHolder) : CardViewRodadasBinding {
        return holder.binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        /*val view = layoutInflater.inflate(
            R.layout.card_view_rodadas,
            parent,
            false
        )*/
        val view = CardViewRodadasBinding.inflate(layoutInflater, parent, false)

        return MyViewHolder(view)

    }

    override fun getItemCount(): Int {
        return this.lista.size
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val binding = bind(holder)

        val status = this.lista[position].status


        if(status == "agendado") {

            binding.textDataHora.text = this.lista[position].data_realizacao
            binding.textHora.text = this.lista[position].hora_realizacao
            binding.textPlacarCasa.visibility = View.INVISIBLE
            binding.textPlacarVisitante.visibility = View.INVISIBLE
            binding.btnStatusJogo.text = "X"
            binding.textTimeCasa.text = this.lista[position].time_mandante.nome_popular
            binding.textTimeVisitante.text = this.lista[position].time_visitante.nome_popular
            binding.textEstadioJogo.text = this.lista[position].estadio.nome_popular

        } else {

            binding.textDataHora.text = this.lista[position].data_realizacao
            binding.textHora.text = this.lista[position].hora_realizacao
            binding.textPlacarCasa.text = this.lista[position].placar_mandante.toString()
            binding.textPlacarVisitante.text = this.lista[position].placar_visitante.toString()
            binding.btnStatusJogo.text = this.lista[position].status
            binding.textTimeCasa.text = this.lista[position].time_mandante.nome_popular
            binding.textTimeVisitante.text = this.lista[position].time_visitante.nome_popular
            binding.textEstadioJogo.text = this.lista[position].estadio.nome_popular

        }

        GlideToVectorYou.init()
            .with(binding.imageTimeCasa.context)
            .load(this.lista[position].time_mandante.escudo.toUri(), binding.imageTimeCasa)

        GlideToVectorYou.init()
            .with(binding.imageTimeVisitante.context)
            .load(this.lista[position].time_visitante.escudo.toUri(), binding.imageTimeVisitante)

    }

}