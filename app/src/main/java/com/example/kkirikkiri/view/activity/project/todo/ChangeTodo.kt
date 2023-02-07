package com.example.kkirikkiri.view.activity.project.todo

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kkirikkiri.R
import com.example.kkirikkiri.module.network.dto.todo.request.ModifyTodoRequest
import com.example.kkirikkiri.viewmodel.TodoModel
import java.util.*

class ChangeTodo : AppCompatActivity() {

    private val model = TodoModel()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_todo_activity)

        val id = intent.getIntExtra("id", 0)
        val name = intent.getStringExtra("name")

        val startDate = findViewById<TextView>(R.id.edit_start_date)
        val endDate = findViewById<TextView>(R.id.edit_end_date)
        val todoName = findViewById<EditText>(R.id.edit_todo_name)
        val write = findViewById<TextView>(R.id.edit_todo)

        startDate.setOnClickListener {
            val cal = Calendar.getInstance()
            val startDateSetListener =
                DatePickerDialog.OnDateSetListener { view, year, month, day ->
                    if (day < 10 && month + 1 < 10) startDate.text = "${year}-0${month + 1}-0${day}"
                    else if (month + 1 < 10) startDate.text = "${year}-0${month + 1}-${day}"
                    else if (day < 10) startDate.text = "${year}-${month + 1}-0${day}"
                    else startDate.text = "${year}-${month + 1}-${day}"
                }

            DatePickerDialog(
                this,
                startDateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        endDate.setOnClickListener {
            val cal = Calendar.getInstance()
            val endDateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, day ->
                if (day < 10 && month + 1 < 10) endDate.text = "${year}-0${month + 1}-0${day}"
                else if (month + 1 < 10) endDate.text = "${year}-0${month + 1}-${day}"
                else if (day < 10) endDate.text = "${year}-${month + 1}-0${day}"
                else endDate.text = "${year}-${month + 1}-${day}"
            }

            DatePickerDialog(
                this,
                endDateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        write.setOnClickListener {
            if (startDate.text.isEmpty() || endDate.text.isEmpty()) Toast.makeText(
                this,
                "날짜를 입력해주세요.",
                Toast.LENGTH_SHORT
            ).show()
            else {
                if (todoName.text.isEmpty()){
                    model.modifyTodo(
                        id,
                        ModifyTodoRequest(
                            name.toString(),
                            endDate.text.toString(),
                            startDate.text.toString()
                        )
                    )
                }else {
                model.modifyTodo(
                    id,
                    ModifyTodoRequest(
                        todoName.text.toString(),
                        endDate.text.toString(),
                        startDate.text.toString()
                    )
                )
                }

            }
            finish()
        }
    }
}