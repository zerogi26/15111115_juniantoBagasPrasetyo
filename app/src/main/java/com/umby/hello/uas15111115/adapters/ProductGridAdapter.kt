package com.umby.hello.uas15111115.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.umby.hello.uisample.R
import com.umby.hello.uas15111115.models.ProductModel

class ProductGridAdapter(var data: ArrayList<ProductModel>): RecyclerView.Adapter<ProductGridAdapter.MyViewHolder>() {
    // Dilakukan ketika data datang,
    // Membuat View
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_product_with_image, viewGroup, false)
        return MyViewHolder(view)
    }

    // Menunjukkan jumlah data
    override fun getItemCount(): Int {
        return data.size
    }

    // Dilakukan ketika data muncul di layar
    // menghubungkan data dengan tampilan
    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        viewHolder.bind(data.get(position))
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView = itemView.findViewById<TextView>(R.id.titleTextView)
        val brandTextView = itemView.findViewById<TextView>(R.id.brandTextView)
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)

        fun bind(product: ProductModel) {
            titleTextView.text = product.name
            brandTextView.text = product.price
            Picasso.get().load(product.imageUrl).into(imageView)
        }
    }
}