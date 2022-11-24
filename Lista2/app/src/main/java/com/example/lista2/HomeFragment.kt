package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var adapter: CrimeListAdapter
    private lateinit var recView: RecyclerView
    private lateinit var crimesList: MutableList<Crime>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInit()

        val layoutManager = LinearLayoutManager(context)
        recView = view.findViewById(R.id.recyclerView)
        recView.layoutManager = layoutManager
        recView.setHasFixedSize(true)
        adapter = CrimeListAdapter(crimesList)
        recView.adapter = adapter
    }

    private fun dataInit(){
        crimesList = generateListOfCrimes()
    }

}