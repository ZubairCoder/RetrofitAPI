package com.example.retrofitapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.retrofitapi.databinding.ActivityPostBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostActivity : AppCompatActivity() {
    private var binding: ActivityPostBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.btnPost?.setOnClickListener {
            postData()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }



    }

    private fun postData() {
        val id = binding?.id?.text.toString()
        val userId = binding?.userId?.text.toString()
        val body = binding?.body?.text.toString()
        val title = binding?.title?.text.toString()

        val data = responsePostItem(
            body,
            id.toInt(),
            title,
            userId.toInt()
        )
        RetrofitInstance.apiInterface.postData(data).enqueue(object : Callback<responsePostItem> {
                override fun onResponse(call: Call<responsePostItem>, response: Response<responsePostItem>,
                ) {
                    Toast.makeText(this@PostActivity, "Data Posted", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<responsePostItem>, t: Throwable) {
                    Toast.makeText(this@PostActivity, "Error while posting", Toast.LENGTH_SHORT).show()
                }
        })
    }
}