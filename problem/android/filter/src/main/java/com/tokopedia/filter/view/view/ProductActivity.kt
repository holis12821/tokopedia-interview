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
     private val  viewModel = ProductListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        /*invoke method is here*/
        viewModel.getData()
        setUpObserve()

        fab.setOnClickListener {
            onFabClicked()
        }
    }

    /*set observe on*/
   private fun setUpObserve() {
        viewModel.onSuccessFix.observe(this, {
            showData(it?.data?.products)
        })

        viewModel.onErrorFix.observe(this, {
            onErrorResponse(it)
        })
    }

    private fun showData(products: List<ProductsItem?>?) {
        product_list.setHasFixedSize(true)
        product_list.layoutManager = GridLayoutManager(applicationContext, 2)
        product_list.itemAnimator = DefaultItemAnimator()
        product_list.adapter = ProductListAdapter(products)
    }

    private fun onErrorResponse(msg: Throwable) {
        Toast.makeText(this,
        "Disconnected to server !! ${msg.message}", Toast.LENGTH_SHORT).show()
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