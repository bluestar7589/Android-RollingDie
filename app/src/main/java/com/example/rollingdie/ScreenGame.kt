package com.example.rollingdie

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment


class ScreenGame : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }
}