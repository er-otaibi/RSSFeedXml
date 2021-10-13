package com.example.rssfeed

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    var topQuestions = mutableListOf<Questions>()
    lateinit var rvMain : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMain = findViewById(R.id.rvMain)
        FetchTopQuestions().execute()
    }

    private inner class FetchTopQuestions : AsyncTask<Void, Void, MutableList<Questions>>() {
        val parser = XMLParser()
        override fun doInBackground(vararg params: Void?): MutableList<Questions> {
            val url = URL("https://stackoverflow.com/feeds")
            val urlConnection = url.openConnection() as HttpURLConnection
            topQuestions = urlConnection.inputStream?.let {
                    parser.parse(it)
                }
                    as MutableList<Questions>
            return topQuestions
        }

        override fun onPostExecute(result: MutableList<Questions>?) {
            super.onPostExecute(result)

            rvMain.adapter = QuestionAdapter(topQuestions as ArrayList<Questions>)
            rvMain.layoutManager = LinearLayoutManager(this@MainActivity)
            rvMain.adapter?.notifyDataSetChanged()
        }

    }

}