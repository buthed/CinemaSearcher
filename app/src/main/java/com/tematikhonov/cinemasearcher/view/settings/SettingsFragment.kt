package com.tematikhonov.cinemasearcher.view.settings

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.tematikhonov.cinemasearcher.R
import com.tematikhonov.cinemasearcher.databinding.SettingsFragmentBinding
import com.tematikhonov.cinemasearcher.model.ADULT_MODE
import kotlinx.android.synthetic.main.settings_fragment.*


class SettingsFragment : Fragment() {

    private var _binding: SettingsFragmentBinding? = null
    private val binding: SettingsFragmentBinding
        get() :SettingsFragmentBinding {
            return _binding!!
        }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.settings_fragment, container, false)
        loadData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contactsButton = view.findViewById<AppCompatButton>(R.id.settings_contact_button)
        contactsButton?.setOnClickListener {                    //TODO не  работает биндинг
            findNavController().navigate(R.id.action_settings_login_to_contactsFragment)
        }
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
        fun newInstance(bundle: Bundle): SettingsFragment {
            val fragment = SettingsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}