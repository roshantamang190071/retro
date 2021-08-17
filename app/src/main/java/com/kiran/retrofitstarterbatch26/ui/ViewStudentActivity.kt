package com.kiran.retrofitstarterbatch26.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RecoverySystem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kiran.retrofitstarterbatch26.R
import com.kiran.retrofitstarterbatch26.ui.model.Student
import com.kiran.retrofitstarterbatch26.ui.repository.StudentRepository
import com.kiran.student.adapter.StudentAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

private lateinit var recyclerview : RecyclerView


class ViewStudentActivity : AppCompatActivity() {

    var studentList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_student)

        recyclerview = findViewById(R.id.recyclerview)


        getAllStudents()

        val adapter = StudentAdapter(this, studentList)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this@ViewStudentActivity, LinearLayoutManager.VERTICAL, true)


    }

    private fun getAllStudents() {
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val repository = StudentRepository()
                val response = repository.getAllStudent()
                if(response.success==true){
                    studentList = response.data!!
                    withContext(Main){
                        Toast.makeText(this@ViewStudentActivity, studentList!!.size.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }catch(ex : Exception){
                withContext(Main){
                    Toast.makeText(this@ViewStudentActivity, ex.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}