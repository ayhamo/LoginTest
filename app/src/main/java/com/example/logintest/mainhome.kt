package com.example.logintest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.logintest.HttpThings.JPayload
import com.example.logintest.HttpThings.RespHandler
import com.loopj.android.http.AsyncHttpClient
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_mainhome.*

class mainhome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainhome)
        setSupportActionBar(toolbar).apply {
            title = "LOG IN"
        }
    }

    fun getAPI(view: View) {
        getClient().get("https://reqres.in/api/users?page=1", object : RespHandler() {
            override fun onMessage(error: String?) {
                // we don't need this handler anymore. It is not important for you
            }

            override fun onSuccess(payload: JPayload?) {
                if (payload != null) {
                    println(((payload.asJArray("data")).getJSONObject(0))["email"])
                }
            }

            override fun onError(error: Throwable?) {
                println(error.toString())
            }
        })
    }

    private fun getClient(vararg mv: Header): AsyncHttpClient {
        val client = AsyncHttpClient()
//        client.loggingLevel = LogInterface.ERROR
//        client.isLoggingEnabled = true
        client.setMaxRetriesAndTimeout(2, 10000)
//        val access = db?.getAsString(DB.ACCESS())
//        if (access != null)
//            client.addHeader("Authorization", "Bearer $access")
//        for (v in mv)
//            client.addHeader(v.key, v.value)
        return client
    }

    fun goToPage(view: View) {
        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }

    fun goToDraw(view: View) {
        val intent = Intent(this, navPage::class.java)
        startActivity(intent)
    }

    fun goTouploding(view: View) {
        val intent = Intent(this, uplode::class.java)
        startActivity(intent)
    }

    fun goTogrid(view: View) {
        val intent = Intent(this, imageGridView::class.java)
        startActivity(intent)
    }

    fun goToskele(view: View) {
        val intent = Intent(this, skeleton::class.java)
        startActivity(intent)
    }


    fun sendMessage(view: View) {
        if (email.text.isNullOrBlank() && password.text.isNullOrBlank()) {
            Toast.makeText(this, "Please fill the info", Toast.LENGTH_SHORT).show()
        } else {

            val message1 = email.text.toString()
            val intent = Intent(this, home::class.java).apply {
                putExtra(EXTRA_MESSAGE, message1)
            }
            startActivity(intent)
        }
    }
}