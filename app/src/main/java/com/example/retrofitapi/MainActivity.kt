package com.example.retrofitapi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapi.databinding.ActivityMainBinding
import com.example.retrofitapi.databinding.GetonedailogueBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private  var binding : ActivityMainBinding? = null
    private  var recyclerView: RecyclerView?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.btnPost?.setOnClickListener {
            val intent = Intent(this,PostActivity::class.java)
            startActivity(intent)
        }
        binding?.btnDailog?.setOnClickListener {
           getOneData()
        }


        recyclerView = binding?.recyclerView
        recyclerView?.layoutManager= LinearLayoutManager(this)
        getData()
    }

    @SuppressLint("MissingInflatedId")
    private fun getOneData() {
        //Alert Dailogue Box

        val builder = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(R.layout.getonedailogue,null)
        builder.setView(view)
        builder.show()
        val btnAdd = view.findViewById<Button>(R.id.btnGet)
        val searchId = view.findViewById<TextView>(R.id.id)
        val searchUerId = view.findViewById<TextView>(R.id.userId)
        val searchBody = view.findViewById<TextView>(R.id.body)
        val searchTitle = view.findViewById<TextView>(R.id.title)

        btnAdd.setOnClickListener {
            val id = searchId.text.toString().toInt()
            RetrofitInstance.apiInterface.getOneData(id).enqueue(object : Callback<responsePostItem>{
                override fun onResponse(
                    call: Call<responsePostItem>, response: Response<responsePostItem>,
                ) {
                    searchUerId.text = response.body()?.userId.toString()
                    searchBody.text = response.body()?.body
                    searchTitle.text = response.body()?.title
                    Toast.makeText(this@MainActivity, response.body()?.title.toString(), Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<responsePostItem>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "OneData not  Responding", Toast.LENGTH_SHORT).show()
                }

            })
        }

    }

    private fun getData() {
        RetrofitInstance.apiInterface.getData().enqueue(object : Call<responsePost>,
            retrofit2.Callback<responsePost> {
            override fun onResponse(call: Call<responsePost>, response: Response<responsePost>) {
                //Toast.makeText(this@MainActivity, "Responded", Toast.LENGTH_SHORT).show()
                recyclerView?.adapter = QuoteAdopter(this@MainActivity, response.body()!!)

            }

            override fun onFailure(call: Call<responsePost>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.localizedMessage}", Toast.LENGTH_SHORT).show()
            }

            override fun clone(): Call<responsePost> {
                TODO("Not yet implemented")
            }

            override fun execute(): Response<responsePost> {
                TODO("Not yet implemented")
            }

            override fun isExecuted(): Boolean {
                TODO("Not yet implemented")
            }

            override fun cancel() {
                TODO("Not yet implemented")
            }

            override fun isCanceled(): Boolean {
                TODO("Not yet implemented")
            }

            override fun request(): Request {
                TODO("Not yet implemented")
            }

            override fun timeout(): Timeout {
                TODO("Not yet implemented")
            }

            override fun enqueue(callback: retrofit2.Callback<responsePost>) {
                TODO("Not yet implemented")
            }
        })

    }

}

