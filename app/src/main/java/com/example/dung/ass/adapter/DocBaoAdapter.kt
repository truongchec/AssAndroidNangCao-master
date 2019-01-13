package com.example.dung.ass.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.dung.ass.R
import com.example.dung.ass.model.News
import kotlinx.android.synthetic.main.item_doc_bao.view.*

class DocBaoAdapter(private val context: Context, private val arrNews: ArrayList<News>) :
    RecyclerView.Adapter<DocBaoAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private lateinit var onClickItemListener: OnClickItemListener


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_doc_bao, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {

        return arrNews.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val news: News = arrNews[p0.adapterPosition]
        p0.txtDate.text = news.date
        p0.txtTitle.text = news.title

        p0.itemView.setOnClickListener {
            onClickItemListener.onClickItem(p0.adapterPosition, news)
        }

        Glide.with(context)
            .load(news.imageUrl)
            .into(p0.imgAvatar)

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgAvatar: ImageView = view.imgAvatar
        val txtTitle: TextView = view.txtTitle
        val txtDate: TextView = view.txtDate

    }

    interface OnClickItemListener {
        fun onClickItem(position: Int, news: News)
    }

    fun setOnClickListener(onClickItemListener: OnClickItemListener) {
        this.onClickItemListener = onClickItemListener
    }

}