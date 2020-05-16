package com.example.kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CourseRecyclerAdpater (private val cursos: List<CourseInfo>)
    : RecyclerView.Adapter<CourseRecyclerAdpater.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_course_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = cursos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val courseInfo = cursos[position]
        holder.textCourse.text = courseInfo.title
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val textCourse = itemView.findViewById<TextView>(R.id.textCourse)
    }
}