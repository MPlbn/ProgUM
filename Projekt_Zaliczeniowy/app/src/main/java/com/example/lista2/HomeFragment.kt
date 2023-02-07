package com.example.lista2

import android.app.DatePickerDialog
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lista2.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.*
import android.text.TextWatcher
import org.w3c.dom.Text

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val startDate = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).format(Calendar.getInstance().time)
    private var currentDate: String? = startDate

    private lateinit var adapter: TaskListAdapter
    private lateinit var recView: RecyclerView
    private lateinit var db: SQLiteDatabase
    private val taskList = mutableListOf<Task>()

    private lateinit var dayDB: SQLiteDatabase
    private val dayList = mutableListOf<Day>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Task db
        db = context!!.openOrCreateDatabase("Tasks", Context.MODE_PRIVATE, null)
        db.execSQL(BasicCommand.SQL_CREATE_TABLE)


        if(arguments != null){
            currentDate = arguments?.getString("DATE")
        }

        val cursor = db.rawQuery("SELECT * FROM Tasks", null)
        while(cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val title = cursor.getString(1)
            val description = cursor.getString(2)
            val tableDate = cursor.getString(3)
            if(tableDate == currentDate){
                val data = Task(id, title, description, tableDate)
                taskList.add(data)
            }

        }
        cursor.close()
        db.close()

        //Day db
        dayDB = context!!.openOrCreateDatabase("Days", Context.MODE_PRIVATE, null)
        dayDB.execSQL(BasicCommand.SQL_CREATE_DAYTABLE)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        val layoutManager = LinearLayoutManager(context)
        recView = view.findViewById(R.id.recyclerView)
        recView.layoutManager = layoutManager
        recView.setHasFixedSize(true)
        adapter = TaskListAdapter(taskList)
        recView.adapter = adapter
        val TVdate = view.findViewById<TextView>(R.id.currentDate_tv)
        val buttonAdd = view.findViewById<Button>(R.id.btn_add)
        val buttonCalendar = view.findViewById<Button>(R.id.btn_calendar)
        val buttonPixelMap = view.findViewById<Button>(R.id.btn_pmap)
        val buttonRateDay = view.findViewById<Button>(R.id.btn_rateDay)
        val dBundle = Bundle()

        TVdate.text = currentDate


        binding.apply{
            buttonCalendar.setOnClickListener {
                val datePickerFragment = CalendarFragment()
                val supportFragmentManager = requireActivity().supportFragmentManager
                supportFragmentManager.setFragmentResultListener(
                    "REQUEST_KEY",
                    viewLifecycleOwner
                ){
                    resultKey, bundle ->
                        if(resultKey == "REQUEST_KEY"){
                            currentDate = bundle.getString("SELECTED_DATE")
                            TVdate.text = currentDate
                            //start
                            taskList.clear()

                            db = context!!.openOrCreateDatabase("Tasks", Context.MODE_PRIVATE, null)
                            db.execSQL(BasicCommand.SQL_CREATE_TABLE)

                            val cursor = db.rawQuery("SELECT * FROM Tasks", null)
                            while(cursor.moveToNext()) {
                                val id = cursor.getInt(0)
                                val title = cursor.getString(1)
                                val description = cursor.getString(2)
                                val tableDate = cursor.getString(3)
                                if(tableDate == currentDate){
                                    val data = Task(id, title, description, tableDate)
                                    taskList.add(data)
                                }

                            }
                            cursor.close()
                            db.close()

                            recView = view.findViewById(R.id.recyclerView)
                            recView.layoutManager = layoutManager
                            recView.setHasFixedSize(true)
                            adapter = TaskListAdapter(taskList)
                            recView.adapter = adapter
                            //fin
                    }
                }
                datePickerFragment.show(supportFragmentManager, "CalendarFragment")
            }
        }


        buttonAdd.setOnClickListener{
            dBundle.putString("DATE", currentDate)
            findNavController().navigate(R.id.action_homeFragment_to_editFragment, dBundle)
        }

        buttonRateDay.setOnClickListener {
            dBundle.putString("DATE", currentDate)
            findNavController().navigate(R.id.action_homeFragment_to_rateDayFragment, dBundle)
        }

        buttonPixelMap.setOnClickListener{
            dBundle.putString("DATE", currentDate)
            findNavController().navigate(R.id.action_homeFragment_to_pixelMapFragment, dBundle)
        }
    }

}