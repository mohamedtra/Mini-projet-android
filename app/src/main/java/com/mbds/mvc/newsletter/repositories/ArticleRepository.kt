package com.mbds.mvc.newsletter.repositories

import com.mbds.mvc.newsletter.services.ArticleService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArticleRepository {
    private val service: ArticleService
    init {
        val retrofit = Retrofit.Builder().apply {
            baseUrl("https://newsapi.org/v2/").
            addConverterFactory(GsonConverterFactory.create())
        }.build()
        service = retrofit.create(ArticleService::class.java)
    }

    fun list(category: String): ArticleResponse{

        val response = service.list(category = category).execute()
        return response.body() ?: ArticleResponse ( "Ok", 0, emptyList())
    }
}