package com.example.roomsample.util

import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.roomsample.R
import com.example.roomsample.data.Employee
import com.example.roomsample.ui.EmployeeListFragment

class NavigationHelper {

    fun openAddNewEmployeeFragment(view: View) =
        Navigation.findNavController(view)
            .navigate(R.id.action_employeeListFragment_to_addNewEmployeeFragment)


    fun saveEmployeeData(view: View) = Navigation.findNavController(view)
        .navigate(R.id.action_addNewEmployeeFragment_to_employeeListFragment)

    fun updateEmployeeData(view: View) = Navigation.findNavController(view)
        .navigate(R.id.action_updateEmployeeFragment_to_employeeListFragment)


    fun openUpdateEmployeeFragment(employee: Employee, view: View) {

        val bundle = bundleOf("employee" to employee)
        Navigation.findNavController(view)
            .navigate((R.id.action_employeeListFragment_to_updateEmployeeFragment), bundle)
    }

    fun navigateToEmployeeListFragment(view: Fragment) = findNavController(view)
        .navigate(R.id.action_updateEmployeeFragment_to_employeeListFragment)

}