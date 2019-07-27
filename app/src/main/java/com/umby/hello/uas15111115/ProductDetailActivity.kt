package com.umby.hello.uas15111115

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import com.umby.hello.uas15111115.models.ProductModel
import com.umby.hello.uisample.R
import kotlinx.android.synthetic.main.activity_product_detail.*

class ProductDetailActivity : AppCompatActivity() {

    lateinit var productModel: ProductModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        productModel = intent.getParcelableExtra("product")

        nameTextView.text = productModel.name
        brandTextView.text = productModel.brand
        priceTextView.text = productModel.price
        descriptionTextView.text = productModel.description

        Picasso.get().load(productModel.imageUrl).into(imageView)
    }
}
