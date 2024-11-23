package com.example.rollingdie

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rollingdie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ScreenGame.OnFragmentInteractionListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)
        Log.d("Main Activity", "Main Activity created")
    }

    override fun onFragmentInteraction(uri: Uri) {
    }
}