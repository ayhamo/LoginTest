package com.example.logintest

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.logintest.HttpThings.JPayload
import com.example.logintest.HttpThings.RespHandler
import com.loopj.android.http.AsyncHttpClient
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_home.*

class home : AppCompatActivity() {


    private lateinit var userAdapter: ExampleAdapter
    var users: ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setSupportActionBar(toolbar1)
        supportActionBar?.apply {
            title = "Welcome " + intent.getStringExtra(EXTRA_MESSAGE)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        shimmer_view_container.startShimmer()
        initRecyclerView()
        getAPI()

    }

    private fun initRecyclerView() {

        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@home)
            userAdapter = ExampleAdapter()
            adapter = userAdapter
            userAdapter.setOnItemClickListener(object : ExampleAdapter.OnItemClickListener {
                override fun onItemClick(position: Int) {
                    Toast.makeText(this@home, "You clicked no. $position", Toast.LENGTH_SHORT).show()
                }

                override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Toast.makeText(this@home, "You clicked no. $position", Toast.LENGTH_SHORT).show()
                }


            })

            val swipGesture = object : SwipGesture(this@home) {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    when (direction) {
                        ItemTouchHelper.LEFT -> {
                            userAdapter.deleteItem(viewHolder.adapterPosition)
                        }
                        ItemTouchHelper.RIGHT -> {
                            val arhive = users[viewHolder.adapterPosition]
                            userAdapter.deleteItem(viewHolder.adapterPosition)
                            userAdapter.addItem(users.size, arhive)
                        }
                    }
                }
            }

            val touchHelper = ItemTouchHelper(swipGesture)
            touchHelper.attachToRecyclerView(recycler_view)

        }
    }

    fun getAPI() {
        getClient().get("https://reqres.in/api/users?page=1", object : RespHandler() {
            override fun onMessage(error: String?) {
                Log.d("onMessage", error.toString())
            }

            override fun onSuccess(payload: JPayload?) {
                if (payload != null) {
                    Log.d(null, payload.toString())
                    //((payload.asJArray("data")).getJSONObject(0))["email"]
                    var size = (payload.get()["per_page"] as Int)
                    for (i in 0 until size) {

                        var name: String = ((payload.asJArray("data")).getJSONObject(i))["first_name"] as String +
                                ((payload.asJArray("data")).getJSONObject(i))["last_name"] as String

                        var email: String = ((payload.asJArray("data")).getJSONObject(i))["email"] as String

                        var image: String = ((payload.asJArray("data")).getJSONObject(i))["avatar"] as String

                        val item = User(image, name, email)
                        users.add(item)
                    }
                    shimmer_view_container.visibility = View.GONE
                    recycler_view.visibility = View.VISIBLE
                    userAdapter.submitList(users)
                    userAdapter.notifyDataSetChanged()
                }
            }


            override fun onError(error: Throwable?) {
                Log.d("Error", error.toString())
            }
        })
    }

    private fun getClient(vararg mv: Header): AsyncHttpClient {
        val client = AsyncHttpClient()
//        client.loggingLevel = LogInterface.ERROR
//        client.isLoggingEnabled = true
//        client.setMaxRetriesAndTimeout(2, 10000)
//        val access = db?.getAsString(DB.ACCESS())
//        if (access != null)
//            client.addHeader("Authorization", "Bearer $access")
//        for (v in mv)
//            client.addHeader(v.key, v.value)
        return client
    }


}