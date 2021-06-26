package com.example.roomsample.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface EmployeeDao {

    @Query("SELECT * from Employee ORDER BY id ASC")
    fun getAllEmployees(): LiveData<List<Employee>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employee: Employee)

    @Query("DELETE FROM Employee")
    suspend fun deleteAllEmployee()

    @Delete
    suspend fun deleteEmployee(employee: Employee)

    @Update
    suspend fun updateEmployee(employee: Employee)
}