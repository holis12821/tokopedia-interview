package com.tokopedia.filter.view.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.tokopedia.filter.R
import com.tokopedia.filter.view.adapter.ProductListAdapter
import com.tokopedia.filter.view.dialogfilter.FilterDialog
import com.tokopedia.filter.view.model.ProductsItem
import com.tokopedia.filter.view.viewmodel.ProductListViewModel
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {
     private val  viewModel: ProductListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        /*invoke method is here*/
        setUpObserve()
        viewModel?.getData()

        fab.setOnClickListener {
            onFabClicked()
        }
    }

    /*set observe on*/
   private fun setUpObserve() {
        viewModel?.showDataFix?.observe(this, {
            showData(it?.data?.products)
        })
        viewModel?.onSuccessFix?.observe(this, {
            onSuccess(it)
        })
        viewModel?.onErrorFix?.observe(this, {
            onErrorResponse(it)
        })
    }

    private fun showData(products: List<ProductsItem?>?) {
        product_list.setHasFixedSize(true)
        product_list.layoutManager = GridLayoutManager(applicationContext, 2)
        product_list.itemAnimator = DefaultItemAnimator()
        product_list.adapter = ProductListAdapter(products)
    }

    private fun onSuccess(status: Int) {
        when(status) {
            200 -> {
                Toast.makeText(this@ProductActivity,
                "Connected to Server", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun onErrorResponse(msg: String) {
        Toast.makeText(this,
        "Disconnected to server !! $msg", Toast.LENGTH_SHORT).show()
    }

    private fun onFabClicked() {
        val fragmentManager = supportFragmentManager
        val filterDialogFragment = FilterDialog()
        val fragment = fragmentManager.findFragmentByTag(filterDialogFragment::class.java.simpleName)
        if (fragment !is FilterDialog) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.frame_filter,
                            filterDialogFragment, FilterDialog::class.java.simpleName)
                    .commit()
        }
    }
}