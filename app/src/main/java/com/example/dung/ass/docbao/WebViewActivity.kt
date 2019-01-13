package com.example.dung.ass.docbao

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.dung.ass.Constant
import com.example.dung.ass.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity(), Constant {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        initView()
    }

    private fun initView() {
        val link = intent.getStringExtra(LINK)
        webView.settings.setSupportZoom(true)
        webView.loadUrl(link)

    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
