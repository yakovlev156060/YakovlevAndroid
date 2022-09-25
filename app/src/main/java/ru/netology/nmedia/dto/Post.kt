package ru.netology.nmedia.dto

data class Post(
    var id: Long = 0,
    var author: String = "",
    var content: String = "",
    var published: String = "",
    var likes: Long = 3,
    var shares: Long = 980,
    var likedByMe: Boolean = false,
)