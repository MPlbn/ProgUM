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
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class editFragment : Fragment() {

    private lateinit var db: SQLiteDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit, container, false)
        db = context!!.openOrCreateDatabase("Tasks", Context.MODE_PRIVATE, null)
        db.execSQL(BasicCommand.SQL_CREATE_TABLE)

        val title = view.findViewById<EditText>(R.id.et_Title)
        val description = view.findViewById<EditText>(R.id.et_Details)
        val id = view.findViewById<TextView>(R.id.tv_taskId)
        val deleteButton = view.findViewById<Button>(R.id.btn_delete)
        val finishButton = view.findViewById<Button>(R.id.btn_finish)

        if(arguments?.size() != 1){
            title.setText("${arguments?.getString("TITLE")}")
            description.setText("${arguments?.getString("DETAILS")}")
            id.text = "Data notatki: ${arguments?.getString("DATE")}"
            deleteButton.isEnabled = true
        }
        else{
            title.setText("Type title here...")
            description.setText("Type description here...")
            id.text = "NOWA NOTATKA"
            deleteButton.isEnabled = false
        }

        finishButton.setOnClickListener {

            val value = ContentValues()
            value.put("title", title.text.toString())
            value.put("description", description.text.toString())
            value.put("date", arguments?.getString("DATE"))
            if(arguments?.size() != 1){
                val selection = "id = ?"
                val selectionArgs = arrayOf(arguments?.getInt("ID").toString())
                val cursor = db.rawQuery("SELECT * FROM Tasks WHERE id = ?", selectionArgs)
                db.update("Tasks", value, selection, selectionArgs)
                cursor.close()
            }
            else
                db.insert("Tasks", null, value)

            db.close()

            val dbundle = Bundle()
            dbundle.putString("DATE", arguments?.getString("DATE"))
            findNavController().navigate(R.id.action_editFragment_to_homeFragment, dbundle)
        }

        deleteButton.setOnClickListener {
            val selection = "id = ?"
            val selectionArgs = arrayOf(arguments?.getInt("ID").toString())

            db.delete("Tasks", selection, selectionArgs)
            db.close()

            val dbundle = Bundle()
            dbundle.putString("DATE", arguments?.getString("DATE"))
            findNavController().navigate(R.id.action_editFragment_to_homeFragment, dbundle)
        }

        return view
    }

}