package com.example.kotlinnews

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_detail.*

class ArticleActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article_detail)

        var navBarTitle = intent.getStringExtra(CustomViewHolder.ARTICLE_TITLE_KEY)
        val articleURL = intent.getStringExtra(CustomViewHolder.ARTICLE_URL_KEY)

        supportActionBar?.title = navBarTitle
        val articleDetailThumbnail = intent.getStringExtra(CustomViewHolder.ARTICLE_THUMBNAIL_KEY)

        if(!TextUtils.isEmpty(articleDetailThumbnail.trim()) && articleDetailThumbnail.trim().isNotEmpty()){
            if(Patterns.WEB_URL.matcher(articleDetailThumbnail).matches()){
                Picasso.get().load(articleDetailThumbnail).into(imageView_article_detail_thumbnail)
            }
        } else {
            imageView_article_detail_thumbnail.visibility = View.GONE
        }

        webView_article.settings.javaScriptEnabled = true
        webView_article.settings.loadWithOverviewMode = true
        webView_article.settings.useWideViewPort = true
        webView_article.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView_article.loadUrl(articleURL)
    }
}