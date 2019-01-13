package com.example.dung.ass.docbao

import android.util.Log
import com.example.dung.ass.model.News
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler
import java.util.ArrayList

class XMLParser : DefaultHandler() {


    /**
     *

    <description>
    <![CDATA[
    <a href="https://vnexpress.net/tin-tuc/giao-duc/thay-giao-cap-ba-co-bay-bai-bao-dang-tap-chi-isi-3824193.html">

    <img width=130 height=100 src="https://i-vnexpress.vnecdn.net/2018/10/15/a6tbrq-1539587719-4322-1539588202_180x108.jpg" >

    </a></br>Buổi tối đi gia sư để lấy tiền làm nghiên cứu sinh, luận án của thầy Nguyễn Văn Yên (Nghệ An) được đánh giá là xuất sắc.
    ]]>
    </description>

     */
    companion object {

        val TAG = "XMLParser"
    }

    private val arrNews = ArrayList<News>()
    private var news: News? = null
    private val TAG_ITEM = "item"
    private val TAG_TITLE = "title"
    private val TAG_DESC = "description"
    private val TAG_DATE = "pubDate"
    private val TAG_LINK = "link"
    private var value: StringBuilder? = null

    @Throws(SAXException::class)
    override fun startElement(uri: String, localName: String, qName: String, attributes: Attributes) {
        super.startElement(uri, localName, qName, attributes)
        if (qName == TAG_ITEM) {
            news = News()
        }
        value = StringBuilder()
    }

    @Throws(SAXException::class)
    override fun characters(ch: CharArray, start: Int, length: Int) {
        super.characters(ch, start, length)
        value!!.append(ch, start, length)
    }

    @Throws(SAXException::class)
    override fun endElement(uri: String, localName: String, qName: String) {
        super.endElement(uri, localName, qName)
        if (news == null) {
            return
        }
        when (qName) {
            TAG_TITLE -> news!!.title = (value!!.toString())
            TAG_DESC -> {
                val src = "src=\""
                var index = value!!.indexOf(src)
                var desc = value!!.substring(index + src.length)
                index = desc.indexOf("\"")
                var img = desc.substring(0, index)
                if (!img.contains("http")) {
                    val origin = "original=\""
                    val indexOrigin = desc.indexOf(origin)
                    img = desc.substring(indexOrigin + origin.length, desc.indexOf("></a>")).replace("\"", "")
                        .trim { it <= ' ' }
                }
                val br = "</br>"
                index = desc.lastIndexOf(br)
                desc = desc.substring(index + br.length)
                Log.d(TAG, img.toString())
                news!!.imageUrl = (img)
            }
            TAG_LINK -> news!!.link = (value!!.toString())
            TAG_DATE -> news!!.date = (value!!.toString())
            TAG_ITEM -> arrNews.add(news!!)
        }
    }

    fun getArrNews(): ArrayList<News> {
        return arrNews
    }
}