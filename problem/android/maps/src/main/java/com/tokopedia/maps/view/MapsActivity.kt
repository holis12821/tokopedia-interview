package com.tokopedia.maps.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.tokopedia.maps.R
import com.tokopedia.maps.model.GmapsItem
import com.tokopedia.maps.viewmodel.GmapsViewModel
import java.io.IOException

/**
 * The "My Location"  button uses GMS Location to set the blue dot representing the user location.
 * Permission for [Manifest.permission.ACCESS_FINE_LOCATION] is requested at run
 * time. If the permission has not been granted, the activity is finished with  an error message.*/

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, OnRequestPermissionsResultCallback {

    private val viewModel = GmapsViewModel()
    private var mapFragment: SupportMapFragment? = null
    private var googleMap: GoogleMap? = null

    private var mLocationPermissionGranted =  false

    private lateinit var textCountryName: TextView
    private lateinit var textCountryCapital: TextView
    private lateinit var textCountryPopulation: TextView
    private lateinit var textCountryCallCode: TextView

    private var editText: EditText? = null
    private var buttonSubmit: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        bindViews()
        initListeners()
        loadMap()
        viewModel.getAllCountries()
        getLocationPermission()
    }

    private fun bindViews() {
        mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        editText = findViewById(R.id.editText)
        buttonSubmit = findViewById(R.id.buttonSubmit)
        textCountryName = findViewById(R.id.txtCountryName)
        textCountryCapital = findViewById(R.id.txtCountryCapital)
        textCountryPopulation = findViewById(R.id.txtCountryPopulation)
        textCountryCallCode = findViewById(R.id.txtCountryCallCode)
    }

    private fun initListeners() {
        buttonSubmit!!.setOnClickListener {
            // TODO
            // search by the given country name, and
            // 1. pin point to the map
            // 2. set the country information to the textViews.
            setUpObserve()
        }
    }

    private fun setUpObserve() {
        viewModel.searchGmapsFixed.observe(this, {
            searchGmaps(it)
        })

        viewModel.onErrorFixed.observe(this, {
            onErrorConnected(it)
        })
    }

    private fun loadMap() {
        mapFragment?.getMapAsync(this)
    }

    @SuppressLint("SetTextI18n")
    private fun searchGmaps(search: Array<GmapsItem?>?) {
        Log.d(javaClass.simpleName, "Starting Google Maps.....")
       val searchLocation = editText?.text.toString().trim()
        var addResList: List<Address>? = null
        if (googleMap != null || searchLocation == "") {
            val geoCoder = Geocoder(this)
            try {
                addResList = geoCoder.getFromLocationName(searchLocation, 1)
            } catch (e: IOException) {
                Log.e("error find location :", e.printStackTrace().toString())
            }
            val address: Address? = addResList?.get(0)

            search?.get(0)?.also {
                address?.countryName = it.nativeName
                address?.phone = it.callingCodes?.get(0)?.toString()
                address?.latitude = it.latlng?.get(0)?.toDouble() ?: 0.0
                address?.longitude = it.latlng?.get(1)?.toDouble() ?: 0.0
            }

            val latLng = address?.latitude?.let { LatLng(it, address.longitude) }

            if (latLng != null) {
                googleMap?.addMarker(MarkerOptions()
                        .position(latLng)
                        .title("Location Based On"))
                googleMap?.moveCamera(CameraUpdateFactory.newLatLng(latLng))

            }
            //set TextView
            textCountryName.text = "Nama Negara : ${address?.countryName}"
            textCountryCapital.text = "Ibukota Negara : ${search?.get(0)?.capital}"
            textCountryPopulation.text = "Jumlah Penduduk : ${search?.get(0)?.population?.toString()}"
            textCountryCallCode.text = "Kode Panggilan : ${address?.phone}"

        }
    }

    private fun onErrorConnected(error: Throwable) {
        Log.e(MapsActivity::class.java.simpleName, error.message.toString())
    }
    /**
     * if on Map ready search location
     * the location has been search in Norwegian Countries, well
     * search location to Norwegian*/
    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap?.isMyLocationEnabled = true
        googleMap?.uiSettings?.isMyLocationButtonEnabled = true
        Toast.makeText(this,
                "Gmaps Ready...", Toast.LENGTH_SHORT).show()
        setUpObserve()
    }

    private fun getLocationPermission() {
        Log.d( javaClass.simpleName,"getLocationPermission: getting location permission")
        // define array for permission
        val permissions = arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        )

        if (ContextCompat.checkSelfPermission(this,
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, COURSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true
                loadMap()
            } else {
                ActivityCompat.requestPermissions(this,
                permissions, LOCATION_PERMISSION_REQUEST_CODE)
            }
        } else {
            ActivityCompat.requestPermissions(this,
            permissions, LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        Log.d(javaClass.simpleName, "OnRequestPermissionsResult: called.")
        mLocationPermissionGranted = false

        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty()) {
                    for (i in grantResults.indices) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionGranted = false
                            Log.d(javaClass.simpleName, "OnRequestPermissionResult: Permission Failed")
                        }
                    }
                    Log.d(javaClass.simpleName, "OnRequestPermissionResult: Permission Granted")
                    mLocationPermissionGranted = true
                    loadMap()
                }
            }
        }
    }

    companion object {
        @JvmStatic
        val FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
        @JvmStatic
        val COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
        @JvmStatic
        val LOCATION_PERMISSION_REQUEST_CODE = 1234
    }
}
