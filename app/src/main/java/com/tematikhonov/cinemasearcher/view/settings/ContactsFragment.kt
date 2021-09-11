package com.tematikhonov.cinemasearcher.view.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.tematikhonov.cinemasearcher.R
import com.tematikhonov.cinemasearcher.databinding.ContactsFragmentBinding


class ContactsFragment : Fragment() {

    private var _binding: ContactsFragmentBinding? = null
    private val binding: ContactsFragmentBinding
        get() :ContactsFragmentBinding {
            return _binding!!
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        _binding = ContactsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.contactsPhoneNumber.setOnClickListener { dialContactPhone() }
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