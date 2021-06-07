package com.example.roomsample.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.roomsample.R
import com.example.roomsample.databinding.FragmentAddNewEmployeeBinding
import com.example.roomsample.viewmodels.AddNewEmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewEmployeeFragment : Fragment() {

    private val addNewEmployeeViewModel: AddNewEmployeeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding: FragmentAddNewEmployeeBinding =
            DataBindingUtil.inflate(
                inflater, R.layout.fragment_add_new_employee, container, false
            )
        dataBinding.addNewEmployeeViewModel=addNewEmployeeViewModel
        return dataBinding.root
    }

}