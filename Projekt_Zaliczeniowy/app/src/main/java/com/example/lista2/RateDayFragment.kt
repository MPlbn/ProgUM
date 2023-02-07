package com.example.lista2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import androidx.navigation.fragment.findNavController


class RateDayFragment : Fragment() {
    private var dateSplit: List<String> = listOf()
    private lateinit var db: SQLiteDatabase
    private var currentRate: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentDate = arguments!!.getString("DATE")
        dateSplit = currentDate!!.split("-")

        return inflater.inflate(R.layout.fragment_rate_day, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val confirmButton = view.findViewById<Button>(R.id.btn_confirmRate)
        val backButton = view.findViewById<Button>(R.id.btn_back_rate_to_home)

        db = context!!.openOrCreateDatabase("Days", Context.MODE_PRIVATE, null)
        db.execSQL(BasicCommand.SQL_CREATE_DAYTABLE)

        val projection = arrayOf("COUNT(*)")
        val selection = "Day = ? AND Month = ? AND Year = ?"
        val selectionArgs: Array<String> = arrayOf(dateSplit[0], dateSplit[1], dateSplit[2])
        val cursor = db.query("Days", projection, selection, selectionArgs, null, null, null)

        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()

        if(count > 0){
            confirmButton.isEnabled = false
            confirmButton.text = "Day already rated"
        }


        val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroup)


        radioGroup.setOnCheckedChangeListener{
            _, checkedID ->
            when(checkedID){
                R.id.radio_1 -> currentRate = 1
                R.id.radio_2 -> currentRate = 2
                R.id.radio_3 -> currentRate = 3
                R.id.radio_4 -> currentRate = 4
                R.id.radio_5 -> currentRate = 5
            }
        }

        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_rateDayFragment_to_homeFragment, arguments)
        }

        confirmButton.setOnClickListener{
            val value = ContentValues()
            value.put("Day", dateSplit[0])
            value.put("Month", dateSplit[1])
            value.put("Year", dateSplit[2])
            value.put("Rate", currentRate)

            db.insert("Days", null, value )
            db.close()

            findNavController().navigate(R.id.action_rateDayFragment_to_homeFragment, arguments)
        }
    }
}

