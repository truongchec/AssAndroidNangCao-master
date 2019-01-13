package com.example.dung.ass.map

import android.Manifest
import android.app.ProgressDialog
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.example.dung.ass.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapActivity : AppCompatActivity(), LocationListener {
    private var myMap: GoogleMap? = null
    private var myProgress: ProgressDialog? = null
    private val enabledLocationProvider: String?
        get() {
            val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager


            val criteria = Criteria()


            val bestProvider = locationManager.getBestProvider(criteria, true)

            val enabled = locationManager.isProviderEnabled(bestProvider)

            if (!enabled) {
                Toast.makeText(this, "No location provider enabled!", Toast.LENGTH_LONG).show()
                Log.i(MYTAG, "No location provider enabled!")
                return null
            }
            return bestProvider
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        myProgress = ProgressDialog(this)
        myProgress!!.setTitle("Map Loading ...")
        myProgress!!.setMessage("Please wait...")
        myProgress!!.setCancelable(true)

        myProgress!!.show()


        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragment) as SupportMapFragment?

        mapFragment!!.getMapAsync { googleMap -> onMyMapReady(googleMap) }


    }

    private fun onMyMapReady(googleMap: GoogleMap) {


        myMap = googleMap


        myMap!!.setOnMapLoadedCallback {
            myProgress!!.dismiss()


            askPermissionsAndShowMyLocation()
        }
        myMap!!.mapType = GoogleMap.MAP_TYPE_NORMAL
        myMap!!.uiSettings.isZoomControlsEnabled = true
        // myMap.setMyLocationEnabled(true);
    }

    private fun askPermissionsAndShowMyLocation() {
        if (Build.VERSION.SDK_INT >= 23) {
            val accessCoarsePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            val accessFinePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)


            if (accessCoarsePermission != PackageManager.PERMISSION_GRANTED || accessFinePermission != PackageManager.PERMISSION_GRANTED) {


                val permissions = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)


                ActivityCompat.requestPermissions(this, permissions,
                    REQUEST_ID_ACCESS_COURSE_FINE_LOCATION)

                return
            }

        }
        this.showMyLocation()
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //
        when (requestCode) {
            REQUEST_ID_ACCESS_COURSE_FINE_LOCATION -> {


                if (grantResults.size > 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "Permission granted!", Toast.LENGTH_LONG).show()


                    this.showMyLocation()
                } else {
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun showMyLocation() {

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val locationProvider = this.enabledLocationProvider ?: return

// Millisecond
        val MIN_TIME_BW_UPDATES: Long = 1000
        // Met
        val MIN_DISTANCE_CHANGE_FOR_UPDATES = 1f

        var myLocation: Location? = null
        try {


            locationManager.requestLocationUpdates(
                locationProvider,
                MIN_TIME_BW_UPDATES,
                MIN_DISTANCE_CHANGE_FOR_UPDATES, this as LocationListener)

            // Lấy ra vị trí.
            myLocation = locationManager
                .getLastKnownLocation(locationProvider)
        } catch (e: SecurityException) {
            Toast.makeText(this, "Show My Location Error: " + e.message, Toast.LENGTH_LONG).show()
            Log.e(MYTAG, "Show My Location Error:" + e.message)
            e.printStackTrace()
            return
        }

        if (myLocation != null) {

            val latLng = LatLng(myLocation.latitude, myLocation.longitude)
            myMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f))

            val cameraPosition = CameraPosition.Builder()
                .target(latLng)             // Sets the center of the map to location user
                .zoom(15f)                   // Sets the zoom
                .bearing(90f)                // Sets the orientation of the camera to east
                .tilt(40f)                   // Sets the tilt of the camera to 30 degrees
                .build()                   // Creates a CameraPosition from the builder
            myMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))


            val option = MarkerOptions()
            option.title("I'm here")
            option.snippet("....")
            option.position(latLng)
            val currentMarker = myMap!!.addMarker(option)
            currentMarker.showInfoWindow()
        } else {
            Toast.makeText(this, "Location not found!", Toast.LENGTH_LONG).show()
            Log.i(MYTAG, "Location not found")
        }


    }

    override fun onLocationChanged(location: Location) {

    }

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

    }

    override fun onProviderEnabled(provider: String) {

    }

    override fun onProviderDisabled(provider: String) {

    }

    companion object {

        private val MYTAG = "MYTAG"
        val REQUEST_ID_ACCESS_COURSE_FINE_LOCATION = 100
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */



}
