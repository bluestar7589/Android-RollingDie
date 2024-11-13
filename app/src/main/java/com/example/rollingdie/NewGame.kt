package com.example.rollingdie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.rollingdie.databinding.GameNewBinding

class NewGame : Fragment() {
    //This holds the binding object for the fragment's layout.
    // It is nullable to allow setting it to null when the view is destroyed.
    private var _binding: GameNewBinding? = null

    //A private read-only property that returns the non-nullable _binding variable.
    // The !! operator is used to assert that _binding is not null when accessed.
    // This property provides a safe way to access the binding object
    // without having to check for nullability every time.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GameNewBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Function is called after the fragment's view has been created
    // and when the button was clicked, it navigates to the RollingDie fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newGameButton.setOnClickListener {
            val action = NewGameDirections.newGameToRollingDie()
            Navigation.findNavController(it).navigate(action)
        }
    }

    // Function is called when the fragment's view is being destroyed
    // which helps to avoid memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}