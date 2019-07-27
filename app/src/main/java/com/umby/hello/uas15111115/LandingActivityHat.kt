package com.umby.hello.uas15111115

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.umby.hello.uas15111115.adapters.ProductGridAdapter
import com.umby.hello.uas15111115.adapters.ProductListAdapter
import com.umby.hello.uas15111115.databases.AppDatabase
import com.umby.hello.uas15111115.models.ProductModel
import com.umby.hello.uas15111115.models.ProductResponseModel
import com.umby.hello.uisample.R
import kotlinx.android.synthetic.main.activity_landing.*
import org.json.JSONObject

class LandingActivityHat : AppCompatActivity() {

    val itemClickListener : (ProductModel) -> Unit = {
        Toast.makeText(this, "Item Click on " + it.id + " at activity", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra("product", it)
        startActivity(intent)
    }

    var data: ArrayList<ProductModel> = arrayListOf()
    var adapter: ProductListAdapter? = null

    lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)


        ButtonShirt.setOnClickListener {
            val intent = Intent(this, LandingActivityShirt::class.java)
            startActivity(intent)
            finish()
        }

        ButtonJeans.setOnClickListener {
            val intent = Intent(this, LandingActivity::class.java)
            startActivity(intent)
            finish()
        }


        val username = intent.getStringExtra("username")
       // val greetingMessage = "Selamat Datang $username"
        //greetingTextView.text = greetingMessage

        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "sampleDb")
            .allowMainThreadQueries()
            .build()

        adapter = ProductListAdapter(data, itemClickListener)

        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
//        recyclerView.adapter = ProductGridAdapter(data)

        loadData()
    }

    fun loadData() {

        downloadDataFromInternet()
    }

    fun downloadDataFromInternet() {
        "http://demo9618857.mockable.io/list/hat"
            .httpGet()
            .responseObject<ProductResponseModel> { request, response, result ->
                val (resultObject, error) = result

                resultObject?.let {
                    runOnUiThread {
                        data.addAll(it.list)
                        adapter?.notifyDataSetChanged()
                    }
                }

                error?.let {
                    Log.e("FuelError", "${it.message}")
                }
            }
    }
}
