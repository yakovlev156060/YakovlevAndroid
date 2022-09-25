package ru.netology.nmedia

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                likeCount?.text = thousands(post.likes)
                shareCount?.text = thousands(post.shares)
                like.setImageResource(
                    if (post.likedByMe){ R.drawable.ic_liked_24} else R.drawable.ic_like_24
                )

            }
        }
        binding.like.setOnClickListener {
            viewModel.like()
        }

        binding.share.setOnClickListener {
            viewModel.share()
        }
    }
}

/*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false
        )
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            author.text = post.author
            publisher.text = post.published
            content.text = post.content
            if (post.likedByMe) {
                like?.setImageResource(R.drawable.ic_liked_24)
            }
            likeCount?.text = thousands(post.likes)
            shareCount?.text = thousands(post.shares)

            like?.setOnClickListener {
                post.likedByMe = !post.likedByMe
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )
                if (post.likedByMe) post.likes++ else post.likes--
                likeCount?.text = thousands(post.likes)
            }

            share?.setOnClickListener {
                post.shares+=10
                shareCount?.text = thousands(post.shares)
            }
        }
    }
}*/