package com.tematikhonov.cinemasearcher.view

import android.Manifest
import android.graphics.Color
import android.location.Geocoder
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.tematikhonov.cinemasearcher.R
import com.tematikhonov.cinemasearcher.databinding.FragmentMainMapsBinding

class MapsFragment : Fragment() {

    private var _binding: FragmentMainMapsBinding? = null
    private val binding: FragmentMainMapsBinding
        get() :FragmentMainMapsBinding {
            return _binding!!
        }

    companion object {
        fun newInstance() = MapsFragment()
    }

    private lateinit var map: GoogleMap
    private val markers: ArrayList<Marker> = arrayListOf()
    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap
        val place = LatLng(55.75, 27.61)
        googleMap.addMarker(MarkerOptions().position(place).title("Marker Start"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(place))

        googleMap.setOnMapLongClickListener { location -> //
            getAddress(location)
            addMarker(location)
            drawLine()
        }
        myLocation(googleMap)
        googleMap.uiSettings.isZoomControlsEnabled = true
    }

    private fun myLocation(map: GoogleMap) {
        context?.let {
            val status = ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PermissionChecker.PERMISSION_GRANTED
            map.isMyLocationEnabled =status
            map.uiSettings.isMyLocationButtonEnabled =status
        }
    }

    private fun drawLine() {
        val last: Int = markers.size - 1
        if (last >= 1) {
            val previous: LatLng = markers[last - 1].position
            val current: LatLng = markers[last].position
            map.addPolyline(
                PolylineOptions()
                    .add(previous, current)
                    .color(Color.RED)
                    .width(5f)
            )
        }
    }


    private fun addMarker(location: LatLng) {
        val marker = setMarker(location, markers.size.toString(), R.drawable.ic_map_pin)
        markers.add(marker)
    }

    private fun setMarker(
        location: LatLng,
        searchText: String,
        resourceId: Int
    ): Marker {
        return map.addMarker(
            MarkerOptions()
                .position(location)
                .title(searchText)
                .icon(BitmapDescriptorFactory.fromResource(resourceId))
        )
    }


    private fun getAddress(location: LatLng) {
        context?.let {
            val geoCoder = Geocoder(context)
            val address = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
            binding.textAddress.text = address[0].getAddressLine(0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
        binding.buttonSearch.setOnClickListener {
            val geoCoder = Geocoder(context)
            val searchText = binding.searchAddress.text.toString()
            val address = geoCoder.getFromLocationName(searchText, 1)
            val location = LatLng(address[0].latitude, address[0].longitude)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
            addMarker(location)
        }
    }
}