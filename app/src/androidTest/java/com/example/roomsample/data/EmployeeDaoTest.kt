package com.example.roomsample.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.roomsample.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@HiltAndroidTest
@SmallTest

class EmployeeDaoTest {

    /**
     * Junit test rule that swap  background executor which is used by architecture component with another one which is running synchronously.
     * without it adding it you will get illegal state exception.
     */
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("employee_DB")
    lateinit var appDataBase: AppDataBase

    lateinit var emloyeeDao: EmployeeDao

    @Before
    fun setup() {
        hiltRule.inject()
        emloyeeDao = appDataBase.employeeDao()
    }

    @After
    fun tearDown() {
        appDataBase.close()
    }

    @Test
    fun insertEmployee() = runBlocking {
        val employee = Employee(1, "Emad", "Abdelmonem", "selime", "35", "IT")
        emloyeeDao.insertEmployee(employee)
        val allEmployees = emloyeeDao.getAllEmployees().getOrAwaitValue()
        assertThat(allEmployees).contains(employee)
    }

    @Test
    fun deleteEmployee() = runBlocking {

        val employee = Employee(1, "Emad", "Abdelmonem", "selime", "35", "IT")
        emloyeeDao.insertEmployee(employee)
        emloyeeDao.deleteEmployee(employee)
        val allEmployees = emloyeeDao.getAllEmployees().getOrAwaitValue()
        assertThat(allEmployees).doesNotContain(employee)

    }

    @Test
    fun deleteAllEmployees() = runBlocking {
        val employee1 = Employee(1, "Emad", "Abdelmonem", "selime", "35", "IT")
        val employee2 = Employee(2, "Ahmed", "Emad", "selime", "28", "HR")
        val employee3 = Employee(3, "Omar", "Emad", "selime", "26", "Business")
        val employee4 = Employee(4, "Karma", "Emad", "selime", "15", "SAles")
        val employee5 = Employee(5, "cal", "john", "peter", "35", "IT")

        emloyeeDao.insertEmployee(employee1)
        emloyeeDao.insertEmployee(employee2)
        emloyeeDao.insertEmployee(employee3)
        emloyeeDao.insertEmployee(employee4)
        emloyeeDao.insertEmployee(employee5)
        emloyeeDao.deleteAllEmployee()

        val allEmployees = emloyeeDao.getAllEmployees().getOrAwaitValue()
        assertThat(allEmployees).isEmpty()
    }

    @Test
    fun updateEmployee() = runBlocking {
        val employee1 = Employee(1, "Emad", "Abdelmonem", "selime", "35", "IT")
        val employee2 = Employee(2, "Ahmed", "Emad", "selime", "28", "HR")
        val employee3 = Employee(3, "Omar", "Emad", "selime", "26", "Business")
        val employee4 = Employee(4, "Karma", "Emad", "selime", "15", "SAles")
        val employee5 = Employee(1, "cal", "john", "peter", "35", "IT")

        emloyeeDao.insertEmployee(employee1)
        emloyeeDao.insertEmployee(employee2)
        emloyeeDao.insertEmployee(employee3)
        emloyeeDao.insertEmployee(employee4)

        emloyeeDao.updateEmployee(employee5)

        val allEmployees = emloyeeDao.getAllEmployees().getOrAwaitValue()
        assertThat(allEmployees).contains(employee5)
    }

}