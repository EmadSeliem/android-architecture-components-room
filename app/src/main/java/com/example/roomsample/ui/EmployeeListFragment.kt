package com.example.roomsample.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.roomsample.R
import com.example.roomsample.adapters.EmployeeListAdapter
import com.example.roomsample.data.Employee
import com.example.roomsample.databinding.FragmentEmployeeListBinding
import com.example.roomsample.util.NavigationHelper
import com.example.roomsample.util.StringUtils
import com.example.roomsample.viewmodels.EmployeeListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class EmployeeListFragment : Fragment() {
    private val employeeListViewModel: EmployeeListViewModel by viewModels()

    @Inject
    lateinit var adapter: EmployeeListAdapter

    @Inject
    lateinit var navigationHelper: NavigationHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding: FragmentEmployeeListBinding =
            DataBindingUtil.inflate(
                inflater, R.layout.fragment_employee_list, container, false
            )
        dataBinding.employeeListViewModel = employeeListViewModel

        employeeListViewModel.employeeList.observe(viewLifecycleOwner, Observer {
            adapter.employeeList = it as ArrayList<Employee>
            dataBinding.employeeList.adapter = adapter

        })
        setHasOptionsMenu(true)
        return dataBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_resource_file, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            employeeListViewModel.removeAllEmployees()
            Toast.makeText(context, R.string.successfully_removed_all_employees, Toast.LENGTH_SHORT)
                .show()
        }
        return super.onOptionsItemSelected(item)
    }
}