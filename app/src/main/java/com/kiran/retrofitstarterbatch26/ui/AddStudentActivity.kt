package com.kiran.retrofitstarterbatch26.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.google.android.material.button.MaterialButton
import com.kiran.retrofitstarterbatch26.R
import com.kiran.retrofitstarterbatch26.ui.model.Student
import com.kiran.retrofitstarterbatch26.ui.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AddStudentActivity : AppCompatActivity() {

    private lateinit var et_fullname : EditText
    private lateinit var et_age : EditText
    private lateinit var et_address : EditText
    private lateinit var btn_save : Button

    var rg_gender : RadioGroup? = null
    lateinit var radioButton : RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        et_fullname = findViewById(R.id.et_fullname)
        et_age = findViewById(R.id.et_age)
        rg_gender = findViewById(R.id.rg_gender)
        et_address = findViewById(R.id.et_address)
        btn_save = findViewById(R.id.btn_save)

        btn_save.setOnClickListener {

            val intSelectButton: Int = rg_gender!!.checkedRadioButtonId

            if (TextUtils.isEmpty(et_fullname.text) || TextUtils.isEmpty(et_age.text)
                || TextUtils.isEmpty(et_address.text)
            ) {
                Toast.makeText(
                    this@AddStudentActivity,
                    "Please fill all information",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (intSelectButton == -1) {
                Toast.makeText(
                    this@AddStudentActivity,
                    "Please select a gender",
                    Toast.LENGTH_SHORT
                )
                    .show()

            } else {

                val fullname: String = et_fullname.text.toString()
                val age: Int = et_age.text.toString().toInt()
                val address = et_address.text.toString()

                radioButton = findViewById(intSelectButton)
                val gender = radioButton.text.toString()


                val student =
                    Student(fullname = fullname, age = age, gender = gender, address = address)

                CoroutineScope(IO).launch {

                try{

                    val repository = UserRepository()
                    val response = repository.
                } catch (ex : Exception){

                }
            }


                Toast.makeText(this@AddStudentActivity, "Student Added", Toast.LENGTH_SHORT).show()
            }
        }


    }
}