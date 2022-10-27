package com.example.kkirikkiri.view.recyclerview.pid.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kkirikkiri.databinding.ItemCommentBinding
import com.example.kkirikkiri.module.network.dto.board.CommentResponse
import com.example.kkirikkiri.module.info.UserInfo

class CommentAdapter(val list : List<CommentResponse>) : RecyclerView.Adapter<CommentAdapter.Holder>(){

    inner class Holder(var binding : ItemCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setComment(comment : CommentResponse) {
            binding.commentId.text = comment.id.toString()
            binding.commentName.text = UserInfo.UserName
            binding.itemCommentContent.text = comment.comment
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = list[position]
        holder.setComment(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}