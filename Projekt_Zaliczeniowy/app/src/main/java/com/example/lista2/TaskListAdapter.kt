package com.example.lista2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

class TaskListAdapter(private val taskList: MutableList<Task>) : RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.list_element, parent, false)
        return TaskListViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        val currentItem = taskList[position]

        holder.title.text = currentItem.title


    }

    override fun getItemCount(): Int = taskList.size

    inner class TaskListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.tv_title)


        init{
            itemView.setOnClickListener{
                val dataBundle = Bundle()
                dataBundle.putString("TITLE", taskList[adapterPosition].title)
                dataBundle.putString("DETAILS", taskList[adapterPosition].description)
                dataBundle.putInt("ID", taskList[adapterPosition].id)
                dataBundle.putString("DATE", taskList[adapterPosition].date)
                Navigation.findNavController(itemView).navigate(R.id.action_homeFragment_to_detailsFragment, dataBundle)
            }
        }
    }

}