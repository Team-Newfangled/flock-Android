package com.example.kkirikkiri.view.activity.project.pid

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kkirikkiri.R
import com.example.kkirikkiri.databinding.ActivityPidViewBinding
import com.example.kkirikkiri.view.recyclerview.RecyclerDecorationHeight
import com.example.kkirikkiri.view.recyclerview.pid.comment.CommentAdapter
import com.example.kkirikkiri.viewmodel.BoardModel
import com.example.kkirikkiri.viewmodel.TodoModel

class PidView : AppCompatActivity() {

    private val binding by lazy { ActivityPidViewBinding.inflate(layoutInflater) }

    private val model = BoardModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val intent = intent
        val id = intent.getIntExtra("id", 0)
        model.findComment(id, 0)
        observe()
        val title = intent.getStringExtra("title")

        binding.pidViewContent.text = title
        binding.pidViewComment.layoutManager = LinearLayoutManager(this)
        binding.pidViewComment.addItemDecoration(RecyclerDecorationHeight(20))

        binding.writePidComment.setOnClickListener {
            val dialog = Dialog(this)

            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setContentView(R.layout.dialog_comment)
            dialog.show()

            val acp = dialog.findViewById<Button>(R.id.comment_acp)
            val cancel = dialog.findViewById<Button>(R.id.comment_cancel)
            val text = dialog.findViewById<TextView>(R.id.comment_content)

            acp.setOnClickListener {
                if (text.text != "") {
                    model.writeComment(id, text.text.toString())
                    dialog.dismiss()
                    refresh()
                } else {
                    Toast.makeText(this, "댓글을 입력해주세요",Toast.LENGTH_SHORT).show()
                }
            }
            cancel.setOnClickListener { dialog.dismiss() }
        }
    }

    private fun observe() {
        model.findCommentData.observe(this) {
            binding.pidViewComment.adapter = CommentAdapter(it)
        }
    }

    private fun refresh() {
        finish()
        overridePendingTransition(0,0)
        startActivity(intent)
        overridePendingTransition(0,0)
    }
}