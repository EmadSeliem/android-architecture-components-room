package com.example.roomsample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roomsample.data.Employee
import com.example.roomsample.data.EmployeeDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val dao: EmployeeDao
) {
    suspend fun insertEmployee(employee: Employee) = dao.insertEmployee(employee)
    fun getAllEmployees(): LiveData<List<Employee>> = dao.getAllEmployees()
    suspend fun updateEmployee(employee: Employee) = dao.updateEmployee(employee)
    suspend fun deleteAllEmployees() = dao.deleteAllEmployee()
    suspend fun deleteEmployee(employee: Employee) = dao.deleteEmployee(employee)


}