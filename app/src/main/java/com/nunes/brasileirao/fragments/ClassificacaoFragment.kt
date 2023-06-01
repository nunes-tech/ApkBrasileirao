package com.nunes.brasileirao.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.nunes.brasileirao.adapter.AdapterView
import com.nunes.brasileirao.databinding.FragmentClassificacaoBinding
import com.nunes.brasileirao.model_tabela.DadosApiItem

class ClassificacaoFragment(val lista:MutableList<DadosApiItem>) :Fragment() {

    lateinit var binding: FragmentClassificacaoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentClassificacaoBinding.inflate(
            inflater,
            container,
            false)

        val adapter = AdapterView()

        binding.rvConteudo.adapter = adapter

        binding.rvConteudo.layoutManager = LinearLayoutManager( container?.context )

        adapter.atualizarLista(lista)

        return  binding.root

       // return super.onCreateView(inflater, container, savedInstanceState)

    }

}