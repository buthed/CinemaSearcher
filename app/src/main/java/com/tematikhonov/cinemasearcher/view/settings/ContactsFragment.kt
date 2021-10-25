package com.tematikhonov.cinemasearcher.view.settings

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import com.tematikhonov.cinemasearcher.R
import com.tematikhonov.cinemasearcher.databinding.ContactsFragmentBinding


class ContactsFragment : Fragment() {

    private var _binding: ContactsFragmentBinding? = null
    private val binding: ContactsFragmentBinding
        get() :ContactsFragmentBinding {
            return _binding!!
        }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        _binding = ContactsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun showRatio() {
        showDialog(getString(R.string.dialog_rationale_title) ,getString(R.string.dialog_rationale_meaasge))
    }


    private fun showDialog(title:String,message: String) {
        context?.let {
            AlertDialog.Builder(it)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Пускай работает корректно") { _, _ ->
                    requestPermission()
                }
                .setNegativeButton("Пускай не работает") { dialog, _ ->
                    dialog.dismiss()
                    requireActivity().finish()
                }
                .create()
                .show()
        }
    }

    private fun showAddressDialog(message: String, location: Location) {
        context?.let {
            AlertDialog.Builder(it)
                .setTitle(R.string.dialog_address_title)
                .setMessage(message)
                .setNegativeButton(R.string.dialog_button_close) { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
        }
    }

    private val REQUEST_CODE = 1
    private fun requestPermission() {
        requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
    }

    private fun checkPermission() {
        context?.let {
            if (ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PermissionChecker.PERMISSION_GRANTED
            ) {
                getLocation()
            } else {

                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                    showRatio()
                } else {
                    requestPermission()
                }
            }
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
                    getLocation()
                } else {
                    context?.let { showRatio() }
                }
            }
        }
    }

    private fun getAddress(context: Context, location: Location){
        val handler = Handler(requireActivity().mainLooper)


        Thread{
            val geoCoder = Geocoder(context)
            val address = geoCoder.getFromLocation(location.latitude,location.longitude,1)
            handler.post { showAddressDialog(address[0].getAddressLine(0),location) }
        }.start()

    }

    private val REFRESH_PERIOD = 6000L
    private val MINIMAL_DISTANCE = 100f

    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            getAddress(requireActivity(),location)
        }

    }

    private fun getLocation(){
        activity?.let { context->
            if (ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PermissionChecker.PERMISSION_GRANTED
            ){
                val locationManager  = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                    val provider = locationManager.getProvider(LocationManager.GPS_PROVIDER)
                    provider?.let{
                        locationManager.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            REFRESH_PERIOD,
                            MINIMAL_DISTANCE,
                            locationListener
                        )
                    }
                }else{

                    val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    if(location==null) {
                        //TODO почему не работает
                        showDialog(getString(R.string.dialog_title_gps_turned_off),getString(R.string.dialog_message_last_location_unknown))
                    }else{
                        getAddress(context,location)
                    }
                }
            }else{
                showRatio()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contactsPhoneNumber.setOnClickListener { dialContactPhone() }
        binding.mainFragmentFABLocation.setOnClickListener { showRatio() }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun dialContactPhone() {
        var uri = Uri.parse("tel:+76665554433")
        startActivity(Intent(Intent.ACTION_DIAL, uri))
        Log.d("Dial", "click")
    }
}