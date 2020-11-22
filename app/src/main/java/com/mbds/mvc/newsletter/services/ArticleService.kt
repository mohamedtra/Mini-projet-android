package com.mbds.mvc.newsletter.services

import com.mbds.mvc.newsletter.repositories.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
    @GET("top-headlines?country=us&apiKey=c4dcbdcf72654deb98c79170d0c94fb9")
    fun list(@Query("category") category: String): Call<ArticleResponse>
}