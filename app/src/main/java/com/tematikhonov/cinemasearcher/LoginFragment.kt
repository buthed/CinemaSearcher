package com.tematikhonov.cinemasearcher

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tematikhonov.cinemasearcher.databinding.CinemaFragmentBinding
import com.tematikhonov.cinemasearcher.databinding.LoginFragmentBinding
import com.tematikhonov.cinemasearcher.model.ADULT_MODE
import com.tematikhonov.cinemasearcher.view.CinemaFragment
import kotlinx.android.synthetic.main.login_fragment.*


class LoginFragment : Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding: LoginFragmentBinding
        get() :LoginFragmentBinding {
            return _binding!!
        }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login_fragment, container, false)
        loadData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onPause() {
        super.onPause()
        saveData()
    }

    private fun saveData() {
        val sharedPreferences = activity?.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.apply {
            putBoolean(ADULT_MODE,adult_switch.isChecked)
        }?.apply()
    }

    private fun loadData(){
        val sharedPreferences = activity?.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
        val savedBoolean = sharedPreferences?.getBoolean(ADULT_MODE,false)
        if (savedBoolean != null) {
            adult_switch.isChecked = savedBoolean
        }
    }

    companion object {
    const val SETTINGS = "Settings"
    private const val preferencesName = "MainPreferences"
        fun newInstance(bundle: Bundle): LoginFragment {
            val fragment = LoginFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

}