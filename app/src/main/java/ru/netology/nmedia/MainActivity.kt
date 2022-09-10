package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.netology.nmedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun thousands(n: Long): String {
            val m: String
            when {
                (n < 1000) -> m = n.toString()
                ((n >= 1000) && (n < 10_000)) -> m =
                    (n / 1000).toString() + "." + (n / 100 - ((n / 1000) * 10)).toString() + "K"
                ((n >= 10_000) && (n < 1_000_000)) -> m = (n / 1000).toString() + "K"
                else -> {
                    var tmp: Long = n / 1000
                    m = (tmp / 1000).toString() + "." + (tmp / 100 - ((tmp / 1000) * 10)).toString() + "M"
                }
            }
            return m
        }

        data class Post(
            var id: Long = 0,
            var author: String = "",
            var content: String = "",
            var publisher: String = "",
            var likes: Long = 0,
            var shares: Long = 0,
            var likedByMe: Boolean = false,
        )
        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            publisher = "21 мая в 18:36",
            likedByMe = false
        )
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            author.text = post.author
            publisher.text = post.publisher
            content.text = post.content
            if (post.likedByMe) {
                like?.setImageResource(R.drawable.ic_liked_24)
            }
            likeCount?.text = thousands(post.likes)
            shareCount?.text = thousands(post.shares)

            root.setOnClickListener {
                Log.d("stuff", "stuff")
            }

            avatar.setOnClickListener {
                Log.d("stuff", "avatar")
            }

            like?.setOnClickListener {
                Log.d("stuff", "like")
                post.likedByMe = !post.likedByMe
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )
                if (post.likedByMe) post.likes++ else post.likes--
                likeCount?.text = thousands(post.likes)
            }

            share?.setOnClickListener {
                Log.d("stuff", "share")
                post.shares+=10
                shareCount?.text = thousands(post.shares)
            }
        }
    }
}