package com.mbds.mvc.newsletter.model

class Article {
    var name: String
    var author: String
    var title: String
    var description: String
    var url: String
    var urlToImage: String
    var publishedAt: String
    var content: String

    constructor(name: String, author: String, title: String, description: String, url: String, urlToImage: String, publishedAt: String, content: String){
        this.name = name
        this.author = author
        this.title = title
        this.description = description
        this.url = url
        this.urlToImage = urlToImage
        this.publishedAt = publishedAt
        this.content = content
    }

}