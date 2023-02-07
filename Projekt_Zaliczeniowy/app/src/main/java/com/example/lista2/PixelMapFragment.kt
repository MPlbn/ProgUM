package com.example.lista2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import org.w3c.dom.Text

class PixelMapFragment : Fragment() {

    private lateinit var db: SQLiteDatabase

    private var dateSplit: List<String> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val currentDate = arguments!!.getString("DATE")
        dateSplit = currentDate!!.split("-")

        return inflater.inflate(R.layout.fragment_pixel_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = context!!.openOrCreateDatabase("Days", Context.MODE_PRIVATE, null)
        db.execSQL(BasicCommand.SQL_CREATE_DAYTABLE)

        var avgRate: Double = 0.0
        var avgBottom = 0

        val selectionargs = arrayOf(dateSplit[1],dateSplit[2])
        val cursor = db.rawQuery("SELECT Rate FROM Days WHERE Month = ? AND Year = ?", selectionargs)
        if(cursor.moveToFirst()){
            do{
                avgRate += cursor.getInt(cursor.getColumnIndex("Rate"))
                avgBottom++
            }while(cursor.moveToNext())
        }
        cursor.close()
        db.close()
        if(avgBottom != 0)
            avgRate /= avgBottom


        val monthTextView = view.findViewById<TextView>(R.id.TV_monthNBR)
        val averageTextView = view.findViewById<TextView>(R.id.TV_average)
        val backButton = view.findViewById<Button>(R.id.btn_back_pixel_to_home)

        monthTextView.text = "Srednia ocena miesiaca: ${dateSplit[1]}-${dateSplit[2]}"
        averageTextView.text = avgRate.toString()


        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_pixelMapFragment_to_homeFragment, arguments)
        }
    }
}

