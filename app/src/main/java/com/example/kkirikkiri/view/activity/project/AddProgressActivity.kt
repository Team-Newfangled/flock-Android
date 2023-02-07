package com.example.kkirikkiri.view.activity.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kkirikkiri.databinding.ActivityAddProgressBinding
import com.example.kkirikkiri.module.network.dto.ContentRequest
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.view.activity.BaseActivity
import com.example.kkirikkiri.viewmodel.TodoModel

class AddProgressActivity : BaseActivity<ActivityAddProgressBinding>({ ActivityAddProgressBinding.inflate(it) }) {

    private val model = TodoModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.saveTodo.setOnClickListener {
            if (binding.todoName.text.isNotEmpty()){
            model.createTodo(UserInfo.projectId!!, ContentRequest(binding.todoName.text.toString()))
            Intent(this, Progress::class.java).run { startActivity(this) }
            finish()
            }else Toast.makeText(this, "Todo이름을 입력해주세요", Toast.LENGTH_SHORT).show()
        }

        binding.addProgressBack.root.setOnClickListener { finish() }
    }
}