package com.mobile.app3.services
import com.mobile.app3.entities.Post
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface PostService{
    @GET("posts/{postId}")
    fun getSinglePost(@Path("postId")postId: String):Call<Post>
    @POST("posts")
    fun setSinglePost (@Body post:Post):Call<Post>
}


