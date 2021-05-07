package com.tokopedia.filter.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tokopedia.filter.R
import com.tokopedia.filter.view.model.ProductsItem
import kotlinx.android.synthetic.main.list_item_product.view.*
import java.text.NumberFormat
import java.util.*

class ProductListAdapter(
        private val products: List<ProductsItem?>?
): RecyclerView.Adapter<ProductListAdapter.ProductHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_product, parent, false)
        return ProductHolder(view)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        //bind view
        val productItem = products?.get(position)
        holder.bindProduct(productItem)
    }

    override fun getItemCount(): Int {
       return products?.size ?: 0 //add elvis operator
    }

    inner class ProductHolder(
           itemView: View
    ) : RecyclerView.ViewHolder(itemView) {
        fun bindProduct(productsItem: ProductsItem?) {
            //bind image with glide
           Glide.with(itemView.context)
                   .load(productsItem?.imageUrl)
                   .into(itemView.icon_product)
            itemView.name_product.text = productsItem?.name
            //set Currency IDR
            val localFormat = Locale("in", "ID")
            val formatIDR = NumberFormat.getCurrencyInstance(localFormat)
            //lamda with argument it
            productsItem?.priceInt?.let {
                itemView.price_product.text = formatIDR.format(it.toLong())
            }
            itemView.address_product.text = productsItem?.shop?.city
            itemView.shopName.text = productsItem?.shop?.name
        }
    }
}