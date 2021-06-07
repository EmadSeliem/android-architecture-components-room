package com.example.roomsample.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.roomsample.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {
    private val fragmentScope = CoroutineScope(Dispatchers.Main)

    /**
     * Setting delay time for splash screen before navigating to next screen.
     */
    private val splashTimeOut: Long = 3000
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        fragmentScope.launch {
            delay(splashTimeOut)
            Navigation.findNavController(view)
                .navigate(R.id.action_splashFragment_to_employeeListFragment)
        }
        return view
    }

}