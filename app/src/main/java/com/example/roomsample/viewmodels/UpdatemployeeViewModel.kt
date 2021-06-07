package com.example.roomsample.viewmodels

import android.text.BoringLayout
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomsample.data.Employee
import com.example.roomsample.repository.MainRepository
import com.example.roomsample.util.NavigationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UpdatemployeeViewModel @Inject constructor(
    private val navigationHelper: NavigationHelper,
    private val mainRepository: MainRepository
) :
    ViewModel() {
    val fNameUpdated = MutableLiveData<String>()
    val mNameUpdated = MutableLiveData<String>()
    val lNameUpdated = MutableLiveData<String>()
    val ageUpdated = MutableLiveData<String>()
    val departmentUpdated = MutableLiveData<String>()
    private val employeeID = MutableLiveData<Int>()

    fun updateEmployeeData(view: View) {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.updateEmployee(setEmployeeData())
            withContext(Dispatchers.Main) {
                navigationHelper.updateEmployeeData(view)
            }
        }
    }

    private fun setEmployeeData(): Employee = Employee(
        employeeID.value?.toInt(),
        fNameUpdated.value.toString(),
        mNameUpdated.value.toString(),
        lNameUpdated.value.toString(),
        ageUpdated.value.toString(),
        departmentUpdated.value.toString()
    )

    fun setData(employee: Employee) {
        employeeID.value = employee.id
        fNameUpdated.value = employee.fName
        mNameUpdated.value = employee.mName
        lNameUpdated.value = employee.lName
        ageUpdated.value = employee.age
        departmentUpdated.value = employee.department
    }

    fun deleteEmployee(employee: Employee) {
        viewModelScope.launch(Dispatchers.IO) {
             mainRepository.deleteEmployee(employee)
        }
    }
}