package com.nunes.brasileirao.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nunes.brasileirao.adapter.AdapterCard
import com.nunes.brasileirao.databinding.FragmentRodadasBinding
import com.nunes.brasileirao.model_rodada.Rodada


class RodadasFragment(val rodadaAtual: Rodada) :Fragment() {

    lateinit var binding: FragmentRodadasBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRodadasBinding.inflate(
            inflater,
            container,
            false
        )

        val adapter = AdapterCard()

        binding.rvConteudo.adapter = adapter
        binding.rvConteudo.layoutManager = LinearLayoutManager(container?.context)
        adapter.atualizarLista(rodadaAtual)

        return binding.root
        //return super.onCreateView(inflater, container, savedInstanceState)
    }

}