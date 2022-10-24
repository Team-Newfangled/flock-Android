package com.example.kkirikkiri.view.activity.project

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kkirikkiri.R
import com.example.kkirikkiri.module.dto.todo.request.ModifyTodoRequest
import com.example.kkirikkiri.viewmodel.TodoModel
import java.util.*

class ChangeTodo : AppCompatActivity() {

    private val model = TodoModel()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_todo_activity)

        val id = intent.getIntExtra("id", 0)

        val startDate = findViewById<TextView>(R.id.edit_start_date)
        val endDate = findViewById<TextView>(R.id.edit_end_date)
        val todoName = findViewById<EditText>(R.id.edit_todo_name)
        val write = findViewById<TextView>(R.id.edit_todo)



        startDate.setOnClickListener {
            val cal = Calendar.getInstance()
            val startDateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, day ->
                startDate.text = "${year}-${month}-${day}"
            }

            DatePickerDialog(this, startDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        endDate.setOnClickListener {
            val cal = Calendar.getInstance()
            val endDateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, day ->
                endDate.text = "${year}-${month}-${day}"
            }

            DatePickerDialog(this, endDateSetListener, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        write.setOnClickListener {
            if (startDate.text.isEmpty() || endDate.text.isEmpty() || todoName.text.isEmpty()) Toast.makeText(this, "내용을 빠짐없이 다 채워주세요!", Toast.LENGTH_SHORT).show()

            model.modifyTodo(id, ModifyTodoRequest(todoName.text.toString(), endDate.text.toString(), startDate.text.toString()))
            finish()
        }
    }
}