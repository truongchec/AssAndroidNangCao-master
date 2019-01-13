package com.example.dung.ass.model

class News {
    var title: String? = null
    var link: String? = null
    var date: String? = null
    var imageUrl: String? = null

    constructor()

    constructor(title: String?, link: String?, date: String?, imageUrl: String?) {
        this.title = title
        this.link = link
        this.date = date
        this.imageUrl = imageUrl
    }


}