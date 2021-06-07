package com.example.roomsample.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.roomsample.R
import com.example.roomsample.data.Employee
import com.example.roomsample.databinding.FragmentAddNewEmployeeBinding
import com.example.roomsample.databinding.FragmentUpdateEmployeeBinding
import com.example.roomsample.util.NavigationHelper
import com.example.roomsample.viewmodels.AddNewEmployeeViewModel
import com.example.roomsample.viewmodels.UpdatemployeeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class UpdateEmployeeFragment : Fragment() {
    private lateinit var employee: Employee
    private val updateEmployeeViewModel: UpdatemployeeViewModel by viewModels()

    @Inject
    lateinit var navigationHelper: NavigationHelper

    private lateinit var dataBinding: FragmentUpdateEmployeeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(
                inflater, R.layout.fragment_update_employee, container, false
            )
        employee = arguments?.getParcelable<Employee>("employee")!!
        if (employee != null) updateEmployeeViewModel.setData(employee)
        dataBinding.updateEmployeeViewModel = updateEmployeeViewModel
        setHasOptionsMenu(true)
        return dataBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_resource_file, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            updateEmployeeViewModel.deleteEmployee(employee)
            Toast.makeText(context, R.string.successfully_deleted, Toast.LENGTH_SHORT).show()
            navigationHelper.navigateToEmployeeListFragment(this)
        }
        return super.onOptionsItemSelected(item)
    }
}