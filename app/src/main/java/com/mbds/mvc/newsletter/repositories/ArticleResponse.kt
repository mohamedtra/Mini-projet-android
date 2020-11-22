package com.mbds.mvc.newsletter.repositories

import com.mbds.mvc.newsletter.model.Article

data class ArticleResponse (
    val status : String ,
    val totalResults : Int ,
    val articles : List<Article>
)




