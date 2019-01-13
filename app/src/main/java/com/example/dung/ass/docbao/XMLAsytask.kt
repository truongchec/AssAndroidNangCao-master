package com.example.dung.ass.docbao

import android.os.AsyncTask
import android.os.Handler
import android.os.Message
import android.util.Log
import com.example.dung.ass.Constant
import com.example.dung.ass.model.News
import java.lang.Exception
import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory

class XMLAsytask(private val handler: Handler) : AsyncTask<String, Void, ArrayList<News>>(), Constant {
    companion object {
        val TAG = "XMLAsytask"
    }

    override fun doInBackground(vararg params: String?): ArrayList<News> {
        var arrayList: ArrayList<News> = ArrayList()
        Log.d(TAG, "Ok...")
        try {
            val factory: SAXParserFactory = SAXParserFactory.newInstance()

            val parser: SAXParser = factory.newSAXParser()
            val handler: XMLParser = XMLParser()
            val link: String = params[0]!!
            parser.parse(link, handler)
            arrayList = handler.getArrNews()

            Log.d(TAG, "Ok...")

        } catch (e: Exception) {

        }
        return arrayList

    }

    override fun onPostExecute(result: ArrayList<News>?) {
        super.onPostExecute(result)

        val messenge = Message()
        messenge.what = WHAT_DATA
        messenge.obj = result
        handler.sendMessage(messenge)

    }
}