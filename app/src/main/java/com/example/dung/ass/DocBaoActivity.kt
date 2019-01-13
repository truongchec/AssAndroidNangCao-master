package com.example.dung.ass

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.dung.ass.adapter.DocBaoAdapter
import com.example.dung.ass.docbao.WebViewActivity
import com.example.dung.ass.docbao.XMLAsytask
import com.example.dung.ass.model.News
import kotlinx.android.synthetic.main.activity_doc_bao.*

class DocBaoActivity : AppCompatActivity(), Constant, DocBaoAdapter.OnClickItemListener {


    private lateinit var arrNews: ArrayList<News>
    private lateinit var docBaoAdapter: DocBaoAdapter
    private lateinit var xmlAsytask: XMLAsytask
    private lateinit var handler: Handler

    companion object {
        val TAG = "DocBaoActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc_bao)



        init()

    }


    private fun init() {
        arrNews = ArrayList()
        val linearLayoutManager = LinearLayoutManager(this@DocBaoActivity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        rcvDocBao.layoutManager = linearLayoutManager

        docBaoAdapter = DocBaoAdapter(this@DocBaoActivity, arrNews)
        rcvDocBao.adapter = docBaoAdapter



        handler = @SuppressLint("HandlerLeak")
        object : Handler() {
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                when (msg!!.what) {
                    WHAT_DATA -> {
                        val array = msg.obj as ArrayList<News>
                        arrNews.addAll(array)
                        docBaoAdapter.notifyDataSetChanged()
                        Log.e(TAG, arrNews.size.toString())
                    }
                }
            }
        }
        xmlAsytask = XMLAsytask(handler)
        xmlAsytask.execute("https://vnexpress.net/rss/giao-duc.rss")

        docBaoAdapter.setOnClickListener(this)
    }


    override fun onClickItem(position: Int, news: News) {


        val intent: Intent = Intent(this@DocBaoActivity, WebViewActivity::class.java)
        intent.putExtra(LINK, news.link)
        startActivity(intent)
        Toast.makeText(this@DocBaoActivity, "Successful", Toast.LENGTH_LONG).show()
    }
}
