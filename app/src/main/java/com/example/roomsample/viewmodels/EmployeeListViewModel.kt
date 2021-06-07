package com.example.roomsample.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomsample.data.Employee
import com.example.roomsample.repository.MainRepository
import com.example.roomsample.util.NavigationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeListViewModel @Inject constructor(
    private val navigationHelper: NavigationHelper,
    private val mainRepository: MainRepository

) :
    ViewModel() {

    var employeeList: LiveData<List<Employee>> = MutableLiveData()

    init {
        employeeList = mainRepository.getAllEmployees()
    }

    fun openAddNewEmployeeFragment(view: View) {

        navigationHelper.openAddNewEmployeeFragment(view)
    }

    fun removeAllEmployees() {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.deleteAllEmployees()
        }
    }
}