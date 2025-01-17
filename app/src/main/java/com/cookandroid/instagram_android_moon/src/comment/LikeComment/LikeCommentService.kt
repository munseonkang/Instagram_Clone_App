package com.cookandroid.instagram_android_moon.src.comment.LikeComment

import com.cookandroid.instagram_android_moon.config.ApplicationClass
import com.cookandroid.instagram_android_moon.src.main.home.likePosting.model.LikeCommentResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LikeCommentService(val likeCommentInterface: LikeCommentInterface) {
    private val likeCommentRetrofitInterface = ApplicationClass.sRetrofit.create(LikeCommentRetrofitInterface::class.java)

    fun tryPostLikeComment(comment_id: Int) {
        likeCommentRetrofitInterface.postLikeComment(comment_id).enqueue(object: Callback<LikeCommentResponse>{
            override fun onResponse(
                call: Call<LikeCommentResponse>,
                response: Response<LikeCommentResponse>
            ) {
                likeCommentInterface.onPostLikeCommentSuccess(response.body() as LikeCommentResponse)
            }

            override fun onFailure(call: Call<LikeCommentResponse>, t: Throwable) {
                likeCommentInterface.onPostLikeCommentFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPatchEditLikeComment(like_id: Int, status: Boolean) {
        likeCommentRetrofitInterface.patchEditLikeComment(like_id, status).enqueue(object: Callback<LikeCommentResponse>{
            override fun onResponse(
                call: Call<LikeCommentResponse>,
                response: Response<LikeCommentResponse>
            ) {
                likeCommentInterface.onPatchEditLikeCommentSuccess(response.body() as LikeCommentResponse)
            }

            override fun onFailure(call: Call<LikeCommentResponse>, t: Throwable) {
                likeCommentInterface.onPatchEditLikeCommentFailure(t.message ?: "통신 오류")
            }
        })
    }
}