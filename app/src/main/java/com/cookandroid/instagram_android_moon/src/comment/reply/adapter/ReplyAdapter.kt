package com.cookandroid.instagram_android_moon.src.comment.reply.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookandroid.instagram_android_moon.databinding.ItemRecyclerCommentBinding
import com.cookandroid.instagram_android_moon.databinding.ItemRecyclerReplyBinding
import com.cookandroid.instagram_android_moon.src.comment.adapter.CommentAdapter
import com.cookandroid.instagram_android_moon.src.comment.model.CommentsResponse
import com.cookandroid.instagram_android_moon.src.comment.model.ResultComments
import com.cookandroid.instagram_android_moon.src.comment.reply.ReplyAdapterInterface
import com.cookandroid.instagram_android_moon.util.ElapsedTimeFunction

class ReplyAdapter(val context: Context, private val resultReplies: MutableList<ResultComments>,
                   private val listener: ReplyingClickListener
) :
    RecyclerView.Adapter<ReplyAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemRecyclerReplyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultComments) {
            Glide.with(context).load(item.profilePicture).into(binding.ivReplyProfileImage)
            binding.apply {
                tvReplyUserName.text = item.profileName
                tvReplyContent.text = item.comment
                tvReplyPostDate.text = ElapsedTimeFunction().run {
                    calculationTime(this.dateTimeToMillSec(item.createdAt))
                }
                tvReplyLikeCount.text = item.likeCount.toString()
                if (item.likeCount == 0) tvReplyLikeCount.visibility = View.INVISIBLE
                if (item.likeOn.on == 1) ckbxReplyLike.isChecked = true
                ivReplyProfileImage.clipToOutline = true
                tvReplyPostReply.setOnClickListener {
                    listener.onReplyingClick(item.groupId)
                    Log.d("GROUPIDTEST", item.groupId.toString())
                    listener.onReplyingClick(item.profileName)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRecyclerReplyBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(resultReplies[position])
    }

    override fun getItemCount(): Int {
        return resultReplies.size
    }

    interface ReplyingClickListener {
        fun onReplyingClick(group_id: Int)
        fun onReplyingClick(nickname: String)
    }
}