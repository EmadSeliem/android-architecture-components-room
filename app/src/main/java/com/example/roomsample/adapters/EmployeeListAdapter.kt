package com.example.roomsample.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomsample.R
import com.example.roomsample.data.Employee
import com.example.roomsample.databinding.EmployeeListItemBinding
import com.example.roomsample.util.NavigationHelper
import com.example.roomsample.util.StringUtils
import javax.inject.Inject

class EmployeeListAdapter @Inject constructor(
    var navigationHelper: NavigationHelper
) :

    RecyclerView.Adapter<EmployeeListAdapter.ViewHolder>() {

    lateinit var employeeList: ArrayList<Employee>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: EmployeeListItemBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.employee_list_item,
            parent, false
        )
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return employeeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(employeeList[position])
    }

    inner class ViewHolder(private val binding: EmployeeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: Employee) {
            binding.employee = employee
            binding.callBack = this@EmployeeListAdapter
            binding.stringUtils=StringUtils.Companion
            binding.executePendingBindings()
        }

    }

    fun updateEmployee(employee: Employee, view: View) {
        navigationHelper.openUpdateEmployeeFragment(employee, view)
    }

}