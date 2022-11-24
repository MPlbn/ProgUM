package com.example.lista2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_details, container, false)
        view.findViewById<TextView>(R.id.tv_studentId).text = "ID studenta: ${ arguments?.getString("ID") }"
        view.findViewById<TextView>(R.id.tv_crimeTitle).text = arguments?.getString("TITLE")
        view.findViewById<TextView>(R.id.tv_crimeDetails).text = arguments?.getString("DETAILS")
        val isSolved = arguments?.getBoolean("ISSOLVED")
        if(isSolved != null){
            if(isSolved){
                view.findViewById<ImageView>(R.id.solveIcon).setImageResource(R.drawable.ic_solved)
            }
            else{
                view.findViewById<ImageView>(R.id.solveIcon).setImageResource(R.drawable.ic_notsolved)
            }
        }


        val button = view.findViewById<Button>(R.id.btn_back)
        button.setOnClickListener{
            findNavController().navigate(R.id.action_detailsFragment_to_homeFragment)
        }

        return view
    }

}