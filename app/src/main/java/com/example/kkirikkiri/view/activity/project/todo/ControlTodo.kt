package com.example.kkirikkiri.view.activity.project.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityControlTodoBinding
import com.example.kkirikkiri.module.info.UserInfo
import com.example.kkirikkiri.module.network.room.RoomImpl
import com.example.kkirikkiri.module.network.room.entity.TodoPercent
import com.example.kkirikkiri.viewmodel.TodoModel

class ControlTodo : AppCompatActivity() {

    private val binding by lazy { ActivityControlTodoBinding.inflate(layoutInflater) }

    private val model = TodoModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getIntExtra("id",0)
        val name = intent.getStringExtra("name")
        val percent = intent.getIntExtra("percent", 0)

        binding.itemTodoName.text = name
        binding.itemControlPercent.text = percent.toString()
        binding.itemPercent.progress = percent
        binding.itemPercent.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, percent: Int, boolean: Boolean) {
             binding.itemControlPercent.text = "$percent"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.todoBackButton.root.setOnClickListener { finish() }
        binding.controlTodoSave.setOnClickListener {
            model.modifyPercent(id, binding.itemControlPercent.text.toString().toInt())
            finish()
        }

    }
}