package com.example.lista2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {

    private lateinit var adapter: TaskListAdapter
    private lateinit var recView: RecyclerView
    private lateinit var db: SQLiteDatabase
    private val taskList = mutableListOf<Task>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        db = context!!.openOrCreateDatabase("Tasks", Context.MODE_PRIVATE, null)
        db.execSQL(BasicCommand.SQL_CREATE_TABLE)

        val cursor = db.rawQuery("SELECT * FROM Tasks", null)
        while(cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val title = cursor.getString(1)
            val description = cursor.getString(2)
            val data = Task(id, title, description)
            taskList.add(data)
        }
        cursor.close()
        db.close()

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        recView = view.findViewById(R.id.recyclerView)
        recView.layoutManager = layoutManager
        recView.setHasFixedSize(true)
        adapter = TaskListAdapter(taskList)
        recView.adapter = adapter

        val buttonAdd = view.findViewById<Button>(R.id.btn_add)
        buttonAdd.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_editFragment)
        }
    }

}