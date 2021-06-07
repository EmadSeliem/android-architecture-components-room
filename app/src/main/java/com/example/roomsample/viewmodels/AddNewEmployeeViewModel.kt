package com.example.roomsample.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomsample.data.Employee
import com.example.roomsample.repository.MainRepository
import com.example.roomsample.util.NavigationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddNewEmployeeViewModel @Inject constructor(
    private val navigationHelper: NavigationHelper,
    private val mainRepository: MainRepository
) :
    ViewModel() {
    val fName = MutableLiveData<String>()
    val mName = MutableLiveData<String>()
    val lName = MutableLiveData<String>()
    val age = MutableLiveData<String>()
    val department = MutableLiveData<String>()
    private val employeeID = MutableLiveData<Int>()

    fun saveEmployeeData(view: View) {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.insertEmployee(setEmployeeData())
            withContext(Dispatchers.Main) {
                navigationHelper.saveEmployeeData(view)
            }
        }
    }

    private fun setEmployeeData(): Employee = Employee(
        employeeID.value?.toInt(),
        fName.value.toString(),
        mName.value.toString(),
        lName.value.toString(),
        age.value.toString(),
        department.value.toString()
    )


}