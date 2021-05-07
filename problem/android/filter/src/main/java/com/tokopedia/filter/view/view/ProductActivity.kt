package com.tokopedia.filter.view.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tokopedia.filter.R
import com.tokopedia.filter.view.adapter.ProductListAdapter
import com.tokopedia.filter.view.model.Data
import com.tokopedia.filter.view.model.ProductsItem
import com.tokopedia.filter.view.model.response.ResponseServer
import com.tokopedia.filter.view.network.ConfigNetwork
import kotlinx.android.synthetic.main.activity_product.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        /*config to handle response*/
        ConfigNetwork.getRetrofit()
                .getDataProduct()
                .enqueue(object : Callback<ResponseServer> {
                    override fun onResponse(call: Call<ResponseServer>,
                                            response: Response<ResponseServer>) {
                        Log.d("response server : ", response.message())
                        //check response server
                        if (response.isSuccessful) {
                            when(response.body()?.statusCode) {
                               200 -> {
                                   val data: Data? = response.body()?.data
                                   showData(data?.products)
                               }
                           }
                        }
                    }

                    override fun onFailure(call: Call<ResponseServer>,
                                           t: Throwable) {
                        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
                        Log.e("error server : ", t.message)
                    }

                })
    }

    private fun showData(products: List<ProductsItem?>?) {
        product_list.adapter = ProductListAdapter(products)
    }
}